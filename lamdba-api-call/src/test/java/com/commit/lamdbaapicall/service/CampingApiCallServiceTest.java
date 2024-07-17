package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CampingApiCallServiceTest {
    @Mock
    private CampingRepository campingRepository;

    @Mock
    private CampingApiClient campingApiClient;

    private CampingApiCallServiceImpl campingApiCallService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        campingApiCallService = new CampingApiCallServiceImpl(campingRepository, campingApiClient);
    }

    @Test
    void fetchAndSaveCampingData_ShouldCallApiClientAndLogData() {
        // Arrange
        List<CampingEntity> mockCampingData = new ArrayList<>();
        mockCampingData.add(new CampingEntity());
        when(campingApiClient.getBaseList(any())).thenReturn(mockCampingData);

        // Act
        campingApiCallService.fetchAndSaveCampingData();

        // Assert
        verify(campingApiClient, times(1)).getBaseList(any());

    }

    @Test
    void setApiCallParameter_ShouldReturnCorrectParameters() {
        // Act
        Map<String, Object> params = campingApiCallService.setApiCallParameter();

        // Assert
        assert params.get("numOfRows").equals(0);
        assert params.get("pageNo").equals(0);
        assert params.get("mobileOS").equals("WINS");
        assert params.get("mobileApp").equals("campus");
        assert params.get("serviceKey").equals("${gocamping.api.decoding-key}");
        assert params.get("type").equals("_json");
    }
}
