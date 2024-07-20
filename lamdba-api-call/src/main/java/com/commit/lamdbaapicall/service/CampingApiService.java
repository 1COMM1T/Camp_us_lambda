package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.dto.CampingFacilitiesDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;

import java.util.List;

public interface CampingApiService {
    String callCampingApi();
    List<CampingDTO> parseCampingList();
    List<CampingFacilitiesDTO> parseCampingFacilitiesList();
    CampingEntity convertDTOToEntity(CampingDTO campingDTO);
    void saveCampingList(List<CampingDTO> campingDTOList);
}
