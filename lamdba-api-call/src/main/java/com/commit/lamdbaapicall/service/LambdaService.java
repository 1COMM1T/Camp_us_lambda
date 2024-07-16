package com.commit.lamdbaapicall.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.nio.charset.StandardCharsets;

@Service
public class LambdaService {

    private final LambdaClient lambdaClient;

    public LambdaService() {
        this.lambdaClient = LambdaClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();;
    }

    public void invokeLambda(String functionName, String payload) {
        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromByteArray(payload.getBytes(StandardCharsets.UTF_8)))
                .build();

        InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);
        String responseString = new String(invokeResponse.payload().asByteArray(), StandardCharsets.UTF_8);

        System.out.println("Lambda response: " + responseString);
    }
}
