package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CampingApiCallServiceImpl implements CampingApiCallService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;

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
        List<CampingEntity> campingData = campingApiClient.getBaseList(params);

        log.info("campingData: " + campingData);
    }

    public Map<String, Object> setApiCallParameter() {

        Map<String, Object> params = new HashMap<>();
        params.put("numOfRows", 0);
        params.put("pageNo", 0);
        params.put("mobileOS", "WINS"); // OS 구분 : IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC(기타)
        params.put("mobileApp", "campus");  // 서비스 명
        params.put("serviceKey", "Au/sJXmqYwNOJf1Wq3qbzEo6OzW4p5KALPI2/nvXgdZWHiHm5jTl2dMdr/R75gxHwTuOeH7pVCOUkUfg4c/kZQ==");
        params.put("type", "_json");

        return params;
    }

    @Override
    public void saveToDatabase(List<CampingEntity> campingData) {

    }
}
