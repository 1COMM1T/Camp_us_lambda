package com.commit.lamdbaapicall.config;

import com.commit.lamdbaapicall.service.LambdaApiCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RequiredArgsConstructor // 생성자 주입 자동화 어노테이션
public class SchedulerConfig {

    private final LambdaApiCallService lambdaApiCallService;

    @Autowired
    public SchedulerConfig(LambdaApiCallService lambdaApiCallService) {
        this.lambdaApiCallService = lambdaApiCallService;
    }

    @Scheduled(cron = "${schedule.cron}") // 12시간마다 동작
    public void scheduledTask() {
        String functionName = "gocamping-api-call";
        String payload = "{}";
        lambdaApiCallService.invokeLambda(functionName, payload);
        log.info("scheduledTask");
    }
}
