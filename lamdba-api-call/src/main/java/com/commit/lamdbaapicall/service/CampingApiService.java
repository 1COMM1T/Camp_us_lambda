package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;

import java.util.List;

public interface CampingApiService {
//    void fetchAndSaveCampingData();
    void saveToDatabase(List<CampingEntity> campingData);

    List<CampingDTO> fetchAndSaveCampingData2();

}
