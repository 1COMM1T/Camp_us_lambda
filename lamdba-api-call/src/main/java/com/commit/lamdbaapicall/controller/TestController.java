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
        String campingData = campingApiService.callCampingApi();

        return campingData;
    }

    @GetMapping("/parse")
    public List<GoCampingDTO> test() {
        List<GoCampingDTO> campingData = campingApiService.parseToDTO();

        return campingData;
    }
//
//    @GetMapping("/parse2")
//    public List<CampingFacilitiesDTO> test2() {
//        List<CampingFacilitiesDTO> facilitiesData = campingApiService.parseCampingFacilitiesList();
//
//        return facilitiesData;
//    }
//
//    @GetMapping("/save")
//    public String save() {
//        List<CampingDTO> campingData = campingApiService.parseCampingList();
//        List<CampingFacilitiesDTO> facilitiesData = campingApiService.parseCampingFacilitiesList();
//
//        campingApiService.saveCampingList(campingData);
//        campingApiService.saveCampingFacilitiesList(facilitiesData);
//
//        return "저장";
//    }
}
