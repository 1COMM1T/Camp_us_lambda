package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CampingApiServiceImpl implements CampingApiService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;
    private final ObjectMapper objectMapper;

    @Value("${gocamping.api.encoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 1000;
    private static final int PAGE_NO = 0;
    private static final String MOBILE_OS = "WIN";
    private static final String MOBILE_APP = "campus";
    private static final String TYPE = "json";

    public CampingApiServiceImpl(CampingRepository campingRepository,
                                 CampingApiClient campingApiClient,
                                 ObjectMapper objectMapper) {
        this.campingRepository = campingRepository;
        this.campingApiClient = campingApiClient;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String callCampingApi() {
        String responseJson = campingApiClient.getBaseList(NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE);;
        log.info("responseJson: {}", responseJson);
        return responseJson;
    }

    @Override
    public List<CampingDTO> parseJsonToDTOList() {
        String campingData = callCampingApi();

        try {
            JsonNode rootNode = objectMapper.readTree(campingData);
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<CampingDTO> campingDTOList = objectMapper.readValue(
                    itemsNode.toString(), new TypeReference<List<CampingDTO>>() {});

            campingDTOList.forEach(dto -> log.info("CampingDTO: {}", dto));

            return campingDTOList;

        } catch (Exception e) {
            log.error("json 데이터 파싱 오류", e);
            return Collections.emptyList();
        }
    }

    @Override
    public CampingEntity convertDTOToEntity(CampingDTO campingDTO) {
        CampingEntity campingEntity = new CampingEntity();

        campingEntity.setCampName(campingDTO.getCampName());
        campingEntity.setLineIntro(campingDTO.getLineIntro());
        campingEntity.setIntro(campingDTO.getIntro());
        campingEntity.setDoName(campingDTO.getDoName());
        campingEntity.setSigunguName(campingDTO.getSigunguName());
        campingEntity.setPostCode(campingDTO.getPostCode());
        campingEntity.setFeatureSummary(campingDTO.getFeatureSummary());
        campingEntity.setInduty(campingDTO.getInduty());
        campingEntity.setAddr(campingDTO.getAddr());
        campingEntity.setAddr(campingDTO.getAddrDetails());
        campingEntity.setMapX(campingDTO.getMapX());
        campingEntity.setMapX(campingDTO.getMapY());
        campingEntity.setTel(campingDTO.getTel());
        campingEntity.setHomepage(campingDTO.getHomepage());
        campingEntity.setStaffCount(campingDTO.getStaffCount());
        campingEntity.setCreatedDate(campingDTO.getCreatedDate());
        campingEntity.setLastModifiedDate(campingDTO.getModifiedDate());

        return campingEntity;
    }

    @Override
    public List<CampingEntity> saveToDatabase(List<CampingDTO> campingDTOList) {

        List<CampingEntity> campingEntityList = campingDTOList.stream()
                .map(this::convertDTOToEntity)
                .collect(Collectors.toList());

        // CampingEntity 리스트를 로그로 출력
        campingEntityList.forEach(entity -> log.info("Saving CampingEntity: {}", entity));

        log.info("====== ======= ======");

        // 데이터베이스에 저장
        campingRepository.saveAll(campingEntityList);

        List<CampingEntity> savedEntities = campingRepository.findAll();
        savedEntities.forEach(campingEntity -> log.info("저장된 엔티티 출력: {}", campingEntity));

        return campingEntityList;
    }
}