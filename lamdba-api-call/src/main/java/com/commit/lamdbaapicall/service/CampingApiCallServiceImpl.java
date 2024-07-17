package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<CampingEntity> campingData =
                campingApiClient.getBaseList(
                        NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE
                );
    }

    @Override
    public List<CampingEntity> fetchAndSaveCampingData2() {
        List<CampingEntity> campingData =
                campingApiClient.getBaseList(
                        NUM_OF_ROWS, PAGE_NO, MOBILE_OS, MOBILE_APP, serviceKey, TYPE
                );

        return campingData;
    }

    @Override
    public void saveToDatabase(List<CampingEntity> campingData) {

    }
}
