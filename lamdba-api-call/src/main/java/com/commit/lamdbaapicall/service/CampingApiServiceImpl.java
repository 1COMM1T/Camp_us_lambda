package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.dto.CampingFacilitiesDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingFacilitiesRepository;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final CampingFacilitiesRepository campingFacilitiesRepository;

    @Value("${gocamping.api.encoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 0;
    private static final int PAGE_NO = 0;
    private static final String VALIDATION_CHECK_OS_KIND = "ETC";
    private static final String VALIDATION_CHECK_APP_NAME = "campus";
    private static final String RESPONSE_FIFE_FORMAT = "json";

    public CampingApiServiceImpl(CampingRepository campingRepository,
                                 CampingApiClient campingApiClient,
                                 ObjectMapper objectMapper, CampingFacilitiesRepository campingFacilitiesRepository) {
        this.campingRepository = campingRepository;
        this.campingApiClient = campingApiClient;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.campingFacilitiesRepository = campingFacilitiesRepository;
    }

    // api 호출하여 json 가져오기
    @Override
    public String callCampingApi() {
        String responseJson =
                campingApiClient.getBaseList(
                        NUM_OF_ROWS, PAGE_NO,
                        VALIDATION_CHECK_OS_KIND,
                        VALIDATION_CHECK_APP_NAME,
                        serviceKey, RESPONSE_FIFE_FORMAT);;

        log.info("responseJson: {}", responseJson.isEmpty());

        return responseJson;
    }

    // 캠핑 데이터 파싱
    @Override
    public List<CampingDTO> parseCampingList() {
        String campingData = callCampingApi();

        try {
            JsonNode rootNode = objectMapper.readTree(campingData);
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<CampingDTO> campingDTOList = objectMapper.readValue(
                    itemsNode.toString(), new TypeReference<List<CampingDTO>>() {}
            );

            campingDTOList.forEach(dto -> log.info("CampingDTO: {}", dto));

            return campingDTOList;

        // exception을 발생 시키는 readTree 부분 조사하여 exception 종류 별로 catch문 작성
        } catch (Exception e) {
            log.error("json 데이터 파싱 오류", e);
            return Collections.emptyList();
        }
    }

    // 캠핑 시설 데이터 파싱
    @Override
    public List<CampingFacilitiesDTO> parseCampingFacilitiesList() {
        String campingFacilitiesData = callCampingApi();

        try {
            JsonNode rootNode = objectMapper.readTree(campingFacilitiesData);
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<CampingFacilitiesDTO> campingFacilitiesDTOList = objectMapper.readValue(
                    itemsNode.toString(), new TypeReference<List<CampingFacilitiesDTO>>() {}
            );

            return campingFacilitiesDTOList;

        } catch (JsonProcessingException e) {
            log.error("json 데이터 파싱 에러");
            throw new RuntimeException(e);
        }
    }

    // 캠핑 데이터 DTO -> Entity로 맵핑
    @Override
    public CampingEntity convertCampingDTO(CampingDTO campingDTO) {
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
        campingEntity.setGeneralSiteCnt(campingDTO.getGeneral_site_cnt());
        campingEntity.setCarSiteCnt(campingDTO.getCar_site_cnt());
        campingEntity.setGlampingSiteCnt(campingDTO.getGlamping_site_cnt());
        campingEntity.setCaravanSiteCnt(campingDTO.getCaravan_site_cnt());
        campingEntity.setPersonalCaravanSiteCnt(campingDTO.getPersonal_caravan_site_cnt());

        return campingEntity;
    }

    // 캠핑 시설 데이터 DTO -> Entity로 변경
    @Override
    public CampingFacilitiesEntity convertCampingFacilitiesDTO(CampingFacilitiesDTO campingFacilitiesDTO) {
        CampingFacilitiesEntity facilitiesEntity = new CampingFacilitiesEntity();

        facilitiesEntity.setInternalFacilitiesList(campingFacilitiesDTO.getInternalFacilitiesList());
        facilitiesEntity.setToiletCnt(campingFacilitiesDTO.getToiletCnt());
        facilitiesEntity.setShowerRoomCnt(campingFacilitiesDTO.getShowerRoomCnt());
        facilitiesEntity.setSinkCnt(campingFacilitiesDTO.getSinkCnt());
        facilitiesEntity.setBrazierClass(campingFacilitiesDTO.getBrazierClass());
        facilitiesEntity.setSupportFacilities(campingFacilitiesDTO.getSupportFacilities());
        facilitiesEntity.setOutdoorActivities(campingFacilitiesDTO.getOutdoorActivities());
        facilitiesEntity.setPetAccess(campingFacilitiesDTO.getPetAccess());
        facilitiesEntity.setFirstImageUrl(campingFacilitiesDTO.getFirstImageUrl());
        facilitiesEntity.setOperationDay(campingFacilitiesDTO.getOperationDay());
        facilitiesEntity.setPersonalTrailerStatus(campingFacilitiesDTO.getPersonalTrailerStatus());
        facilitiesEntity.setPersonalCaravanStatus(campingFacilitiesDTO.getPersonalCaravanStatus());
        facilitiesEntity.setRentalGearList(campingFacilitiesDTO.getRentalGearList());

        return facilitiesEntity;
    }

    // fk 찾기
    private CampingEntity findCampingEntityForDTO(CampingFacilitiesDTO dto, List<CampingEntity> campingEntities) {

        return campingEntities.stream()
                .filter(entity -> entity.getCampId() == dto.getCampId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CampingEntity를 찾을 수 없습니다. CampingFacilitiesDTO의 camp_id: " + dto.getCampId()));
    }


    // camping 테이블 저장
    @Override
    public void saveCampingList(List<CampingDTO> campingDTOList) {

        List<CampingEntity> campingEntityList = campingDTOList.stream()
                .map(this::convertCampingDTO)
                .collect(Collectors.toList());

        campingRepository.saveAll(campingEntityList);
    }

    // campingFacilities 테이블 저장
    @Override
    public void saveCampingFacilitiesList(List<CampingFacilitiesDTO> campingFacilitiesDTOList) {

        List<CampingFacilitiesEntity> campingFacilitiesEntityList = campingFacilitiesDTOList.stream()
                .map(this::convertCampingFacilitiesDTO)
                .collect(Collectors.toList());

        campingFacilitiesRepository.saveAll(campingFacilitiesEntityList);
    }

}