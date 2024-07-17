package com.commit.lamdbaapicall.openfeign;

import com.commit.lamdbaapicall.dto.CampingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CampingApiClient", url = "${gocamping.api.base-url}")
public interface CampingApiClient {
    @GetMapping("/baselist")
    List<CampingDTO> getBaseList(
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("MobileOS") String mobileOS,
            @RequestParam("MobileApp") String mobileApp,
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("_type") String type
    );

}
