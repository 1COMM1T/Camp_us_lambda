package com.commit.lamdbaapicall.openfeign;

import com.commit.lamdbaapicall.entity.CampingEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "CampingApiClient", url = "${gocamping.api.base-url}")
public interface CampingApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/basedList")
    List<CampingEntity> getBaseList(
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("MobileOS") String mobileOS,
            @RequestParam("MobileApp") String mobileApp,
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("_type") String type
    );

}
