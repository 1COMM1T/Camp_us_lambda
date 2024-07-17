package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.commit.lamdbaapicall.view.CampingApiResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CampingApiServiceImpl implements CampingApiService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;

    @Value("${gocamping.api.decoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 5000;
    private static final int PAGE_NO = 0;
    private static final String MOBILE_OS = "WIN";
    private static final String MOBILE_APP = "campus";
    private static final String TYPE = "json";

    @Autowired
    public CampingApiServiceImpl(
            CampingRepository campingRepository,
            CampingApiClient campingApiClient) {
        this.campingRepository = campingRepository;
        this.campingApiClient = campingApiClient;
    }

//    @Override
////    @Scheduled(cron = "${schedule.cron}")
//    public void fetchAndSaveCampingData() {
//        List<CampingEntity> campingData =
//                campingApiClient.getBaseList(
//                        NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE
//                );
//    }

    @Override
    public List<CampingDTO> fetchAndSaveCampingData2() {
        String campingData =
                campingApiClient.getBaseList(
                        NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE
                );

        List<CampingDTO> parsedCampingData = parseCampingDTOList(campingData);

        int count = parsedCampingData.size();
        log.info(String.valueOf(count));

        return parsedCampingData;
    }

    @Override
    public void saveToDatabase(List<CampingEntity> campingData) {

    }

    private List<CampingDTO> parseCampingDTOList(String responseBody) {
        Gson gson = new Gson();
        Type responseType = new TypeToken<CampingApiResponse>() {
        }.getType();
        CampingApiResponse apiResponse = gson.fromJson(responseBody, responseType);

        if (apiResponse != null
                && apiResponse.getResponse() != null
                && apiResponse.getResponse().getBody() != null) {
            return apiResponse.getResponse().getBody().getItems().getItem();
        }
        return Collections.emptyList();
    }
}

