package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;

import java.util.List;

public interface CampingApiService {
    String callCampingApi();
    List<CampingDTO> parseJsonToDTOList();
    CampingEntity convertDTOToEntity(CampingDTO campingDTO);
    void saveToDatabase(List<CampingDTO> campingDTOList);
}
