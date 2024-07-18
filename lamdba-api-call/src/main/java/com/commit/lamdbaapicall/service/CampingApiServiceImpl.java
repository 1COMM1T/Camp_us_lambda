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

@Service
@Slf4j
public class CampingApiServiceImpl implements CampingApiService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;
    private final ObjectMapper objectMapper;

    @Value("${gocamping.api.decoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 5000;
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
        return campingApiClient.getBaseList(NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE);
    }

    @Override
    public List<CampingDTO> fetchCampingData() {
        String campingData = callCampingApi();

        try {
            // Json 루트 객체를 읽어들인다.
            JsonNode rootNode = objectMapper.readTree(campingData);
            // "response" -> "body" -> "items" -> "item" 노드를 찾는다.
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            // itemsNode를 List<CampingDTO>로 역직렬화한다.
            List<CampingDTO> campingDTOList = objectMapper.readValue(
                    itemsNode.toString(), new TypeReference<List<CampingDTO>>() {});

            // DTO 리스트를 로그에 출력한다.
            campingDTOList.forEach(dto -> log.info("Camping DTO: {}", dto));

            return campingDTOList;

        } catch (Exception e) {
            log.error("json 데이터 파싱 오류", e);
            return Collections.emptyList();
        }
    }


    @Override
    public void saveToDatabase(List<CampingEntity> campingData) {
        // 구현 필요
    }
}