package com.commit.lamdbaapicall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerConfig {

    @Scheduled(cron = "${schedule.cron}")
    public void scheduledTask() {
        log.info("scheduledTask");
    }
}
