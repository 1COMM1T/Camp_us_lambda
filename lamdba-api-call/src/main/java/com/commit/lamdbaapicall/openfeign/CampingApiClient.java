package com.commit.lamdbaapicall.openfeign;

import com.commit.lamdbaapicall.entity.CampingEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Component
//@FeignClient(name = "CampingApiClient", url = "${gocamping.api.base-url}")
@FeignClient(name = "CampingApiClient", url = "http://apis.data.go.kr/B551011/GoCamping")
public interface CampingApiClient {
    @GetMapping("/baselist")
    List<CampingEntity> getBaseList(Map<String, Object> params);

}
