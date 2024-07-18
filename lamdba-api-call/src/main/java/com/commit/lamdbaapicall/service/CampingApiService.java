package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;

import java.util.List;

public interface CampingApiService {
    void saveToDatabase(List<CampingDTO> campingDTOList);
    String callCampingApi();
    List<CampingDTO> fetchCampingData();

}
