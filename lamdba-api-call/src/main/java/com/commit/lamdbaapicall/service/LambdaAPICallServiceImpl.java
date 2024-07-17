package com.commit.lamdbaapicall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class LambdaAPICallServiceImpl implements LambdaAPICallService {

    private final LambdaClient lambdaClient;
    private final RestTemplate restTemplate;

    @Value("${gocamping.api.base-url}")
    private String baseUrl;

    @Value("${gocamping.api.key}")
    private String apiKey;

    @Autowired
    public LambdaAPICallServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.lambdaClient = LambdaClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();;
    }

    @Override
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
