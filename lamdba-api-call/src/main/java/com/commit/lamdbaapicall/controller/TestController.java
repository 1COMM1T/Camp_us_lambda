package com.commit.lamdbaapicall.controller;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.service.CampingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private final CampingApiService campingApiService;

    @Autowired
    public TestController(CampingApiService campingApiService) {
        this.campingApiService = campingApiService;
    }

    @GetMapping("/call")
    public String call() {
        String campingData = campingApiService.callCampingApi();

        return campingData;
    }

    @GetMapping("/parse")
    public List<CampingDTO> test() {
        List<CampingDTO> campingData = campingApiService.parseJsonToDTOList();

        return campingData;
    }

    @GetMapping("/save")
    public String saveData() {
        List<CampingDTO> campingData = campingApiService.parseJsonToDTOList();

        campingApiService.saveToDatabase(campingData);

        return "실행됨";
    }
}
