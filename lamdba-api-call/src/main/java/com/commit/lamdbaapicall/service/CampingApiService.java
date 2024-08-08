package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.GoCampingDTO;

import java.util.List;

public interface CampingApiService {
    String callCampingApi();
    void saveCampingData();
    List<GoCampingDTO> parseToDTO();
}
