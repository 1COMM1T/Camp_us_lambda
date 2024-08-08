package com.commit.lamdbaapicall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients(basePackages = "com.commit.lamdbaapicall.openfeign")
public class LamdbaApiCallApplication {

    public static void main(String[] args) {
        SpringApplication.run(LamdbaApiCallApplication.class, args);
    }
}
