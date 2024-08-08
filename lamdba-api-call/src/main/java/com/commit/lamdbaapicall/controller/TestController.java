package com.commit.lamdbaapicall.controller;

import com.commit.lamdbaapicall.dto.GoCampingDTO;
import com.commit.lamdbaapicall.service.CampingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return campingApiService.callCampingApi();
    }

    @GetMapping("/parse")
    public List<GoCampingDTO> parse() {
        return campingApiService.parseToDTO();
    }
}
