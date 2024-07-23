package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.dto.CampingFacilitiesDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingFacilitiesRepository;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.commit.lamdbaapicall.service.CampingApiServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampingApiServiceImplTest {

    @Mock
    private CampingRepository campingRepository;

    @Mock
    private CampingFacilitiesRepository campingFacilitiesRepository;

    @Mock
    private CampingApiClient campingApiClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CampingApiServiceImpl campingApiService;

    private List<CampingDTO> campingDTOList;
    private List<CampingFacilitiesDTO> campingFacilitiesDTOList;

    @BeforeEach
    void setUp() {
        CampingDTO campingDTO = new CampingDTO();
        campingDTO.setCampName("Test Name");
                campingDTOList = List.of(campingDTO);

        CampingFacilitiesDTO campingFacilitiesDTO = new CampingFacilitiesDTO();
        campingFacilitiesDTO.setFacilityName("Test Facility");
        campingFacilitiesDTOList = List.of(campingFacilitiesDTO);
    }

    @Test
    void testSaveCampingList() {
        // Arrange
        List<CampingEntity> campingEntities = campingDTOList.stream()
                .map(campingApiService::convertCampingDTO)
                .collect(Collectors.toList());

        // Act
        campingApiService.saveCampingList(campingDTOList);

        // Assert
        verify(campingRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testSaveCampingFacilitiesList() {
        // Arrange
        List<CampingFacilitiesEntity> campingFacilitiesEntities = campingFacilitiesDTOList.stream()
                .map(campingApiService::convertCampingFacilitiesDTO)
                .collect(Collectors.toList());

        // Act
        campingApiService.saveCampingFacilitiesList(campingFacilitiesDTOList);

        // Assert
        verify(campingFacilitiesRepository, times(1)).saveAll(anyList());
    }

//    @Test
//    void testCallCampingApi() {
//        // Arrange
//        String expectedJson = "{}";
//        when(campingApiClient.getBaseList(anyInt(), anyInt(), anyString(), anyString(),
//                        eq("Au%2FsJXmqYwNOJf1Wq3qbzEo6OzW4p5KALPI2%2FnvXgdZWHiHm5jTl2dMdr%2FR75gxHwTuOeH7pVCOUkUfg4c%2FkZQ%3D%3D"),
//                eq("json")))
//                .thenReturn(expectedJson);
//
//        // Act
//        String responseJson = campingApiService.callCampingApi();
//
//        // Assert
//        assertEquals(expectedJson, responseJson);
//    }

    @Test
    void testParseCampingList() throws Exception {
        // Arrange
        String campingData = "{\"response\":{\"body\":{\"items\":{\"item\":[]}}}}";
        when(campingApiClient.getBaseList(0, 0, "ETC",
                "campus",
                "Au%2FsJXmqYwNOJf1Wq3qbzEo6OzW4p5KALPI2%2FnvXgdZWHiHm5jTl2dMdr%2FR75gxHwTuOeH7pVCOUkUfg4c%2FkZQ%3D%3D"
                , "json"))
                .thenReturn(campingData);

        JsonNode itemsNode = mock(JsonNode.class);
        when(objectMapper.readTree(campingData)).thenReturn(mock(JsonNode.class));
        when(objectMapper.readTree(campingData).path("response").path("body").path("items").path("item"))
                .thenReturn(itemsNode);
        when(objectMapper.readValue(itemsNode.toString(), new TypeReference<List<CampingDTO>>() {}))
                .thenReturn(campingDTOList);

        // Act
        List<CampingDTO> result = campingApiService.parseCampingList();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Camp", result.get(0).getCampName());
    }

    @Test
    void testParseCampingFacilitiesList() throws Exception {
        // Arrange
        String campingFacilitiesData = "{\"response\":{\"body\":{\"items\":{\"item\":[]}}}}";
        when(campingApiClient.getBaseList(anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(campingFacilitiesData);

        JsonNode itemsNode = mock(JsonNode.class);
        when(objectMapper.readTree(campingFacilitiesData)).thenReturn(mock(JsonNode.class));
        when(objectMapper.readTree(campingFacilitiesData).path("response").path("body").path("items").path("item"))
                .thenReturn(itemsNode);
        when(objectMapper.readValue(itemsNode.toString(), new TypeReference<List<CampingFacilitiesDTO>>() {}))
                .thenReturn(campingFacilitiesDTOList);

        // Act
        List<CampingFacilitiesDTO> result = campingApiService.parseCampingFacilitiesList();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Facility", result.get(0).getFacilityName());
    }
}