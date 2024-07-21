package com.commit.lamdbaapicall.config;

import com.commit.lamdbaapicall.service.LambdaAPICallService;
import com.commit.lamdbaapicall.service.LambdaAPICallServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerConfig {

//    private final LambdaAPICallService lambdaAPICallService;
//
//    @Autowired
//    public SchedulerConfig(LambdaAPICallService lambdaAPICallService) {
//        this.lambdaAPICallService = lambdaAPICallService;
//    }

//    @Scheduled(cron = "${schedule.cron}")
//    public void scheduledTask() {
//        String functionName = "gocamping-api-call";
//        String payload = "{}";
//        lambdaAPICallService.invokeLambda(functionName, payload);
//    }
}
