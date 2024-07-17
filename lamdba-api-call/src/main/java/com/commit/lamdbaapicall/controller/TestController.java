package com.commit.lamdbaapicall.controller;

import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.service.CampingApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final CampingApiCallService campingApiCallService;

    @Autowired
    public TestController(CampingApiCallService campingApiCallService) {
        this.campingApiCallService = campingApiCallService;
    }

    @GetMapping("/test")
    public List<CampingEntity> test() {
        List<CampingEntity> campingData = campingApiCallService.fetchAndSaveCampingData2();

        return campingData;
    }
}
