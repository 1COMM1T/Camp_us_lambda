package com.commit.lamdbaapicall.config;

import com.commit.lamdbaapicall.service.LambdaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerConfig {

    private final LambdaService lambdaService;


    @Autowired
    public SchedulerConfig(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }

    @Scheduled(cron = "${schedule.cron}")
    public void scheduledTask() {
        String functionName = "gocamping-api-call";
        String payload = "{}";
        lambdaService.invokeLambda(functionName, payload);
    }
}
