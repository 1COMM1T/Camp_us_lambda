package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;

import java.util.List;

public interface CampingApiCallService {
    void fetchAndSaveCampingData();
    void saveToDatabase(List<CampingEntity> campingData);

    List<CampingEntity> fetchAndSaveCampingData2();

}
