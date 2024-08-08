package com.commit.lamdbaapicall.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "CampingApiClient", url = "${gocamping.api.base-url}")
public interface CampingApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/basedList")
    String getBaseList(
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("MobileOS") String mobileOS,
            @RequestParam("MobileApp") String mobileApp,
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("_type") String type
    );
}
