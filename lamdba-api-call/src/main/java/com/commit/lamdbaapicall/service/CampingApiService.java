package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.dto.CampingFacilitiesDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;

import java.util.List;

public interface CampingApiService {
    String callCampingApi();
//    List<CampingDTO> parseCampingList();
//    List<CampingFacilitiesDTO> parseCampingFacilitiesList();
//    CampingEntity convertCampingDTO(CampingDTO campingDTO);
//    CampingFacilitiesEntity convertCampingFacilitiesDTO(CampingFacilitiesDTO campingFacilitiesDTO);
//    void saveCampingList(List<CampingDTO> campingDTOList);
//    void saveCampingFacilitiesList(List<CampingFacilitiesDTO> campingFacilitiesDTOList);

    void saveCampingData();
}
