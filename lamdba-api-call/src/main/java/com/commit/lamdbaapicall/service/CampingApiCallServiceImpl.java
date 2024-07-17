package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CampingApiCallServiceImpl implements CampingApiCallService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;

    @Value("${gocamping.api.decoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 0;
    private static final int PAGE_NO = 0;
    private static final String MOBILE_OS = "WIN";
    private static final String MOBILE_APP = "campus";
    private static final String TYPE = "json";

    @Autowired
    public CampingApiCallServiceImpl(
            CampingRepository campingRepository,
            CampingApiClient campingApiClient) {
        this.campingRepository = campingRepository;
        this.campingApiClient = campingApiClient;
    }

    @Override
//    @Scheduled(cron = "${schedule.cron}")
    public void fetchAndSaveCampingData() {
        Map<String, Object> params = setApiCallParameter();
        List<CampingEntity> campingData = campingApiClient.getBaseList(NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE);


//        System.out.println("campingData: " + campingData);
        log.info("campingData: " + campingData);
    }

    @Override
    public List<CampingEntity> fetchAndSaveCampingData2() {
        Map<String, Object> params = setApiCallParameter();
        List<CampingEntity> campingData = campingApiClient.getBaseList(NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE);

        return campingData;
    }

    public Map<String, Object> setApiCallParameter() {

        Map<String, Object> params = new HashMap<>();
        params.put("numOfRows", 0);
        params.put("pageNo", 0);
        params.put("mobileOS", "WINS"); // OS 구분 : IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC(기타)
        params.put("mobileApp", "campus");  // 서비스 명
        params.put("serviceKey", "${gocamping.api.decoding-key}");
        params.put("type", "json");

        return params;
    }

    @Override
    public void saveToDatabase(List<CampingEntity> campingData) {

    }
}
