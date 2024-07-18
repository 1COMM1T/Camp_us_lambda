package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CampingApiServiceImplTest {

    @Mock
    private CampingRepository campingRepository;

    @Mock
    private CampingApiClient campingApiClient;

    @InjectMocks
    private CampingApiServiceImpl campingApiService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testParseJsonToDTOList() throws Exception {

        String mockApiResponse = createMockApiResponse();
        when(campingApiClient.getBaseList(anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockApiResponse);

        List<CampingDTO> campingDTOList = campingApiService.parseJsonToDTOList();

        assertEquals(2, campingDTOList.size());

        CampingDTO firstDTO = campingDTOList.get(0);
        assertEquals("Camp Site 1", firstDTO.getCampName());
        assertEquals("한줄 소개글 1", firstDTO.getLineIntro());
        assertEquals("소개글 1", firstDTO.getIntro());
        assertEquals("충청도", firstDTO.getProvinceName());
        assertEquals("단양군", firstDTO.getDistrictName());
        assertEquals("12345", firstDTO.getPostCode());
        assertEquals("캠핑장 특징을 요약한 글", firstDTO.getFeatureSummary());
        assertEquals("업종", firstDTO.getInduty());
        assertEquals("충청도 단양군", firstDTO.getAddr());
        assertEquals("상세주소", firstDTO.getAddrDetails());
        assertEquals(127.58, firstDTO.getMapX());
        assertEquals(99.021, firstDTO.getMapY());
        assertEquals("042-1234-1234", firstDTO.getTel());
        assertEquals("http://www.camp.com", firstDTO.getHomepage());
        assertEquals(2, firstDTO.getStaffCount());


        CampingDTO secondDTO = campingDTOList.get(1);
        assertEquals("Camp Site 1", secondDTO.getCampName());
        assertEquals("한줄 소개글 1", secondDTO.getLineIntro());
        assertEquals("소개글 2", secondDTO.getIntro());
        assertEquals("경기도", secondDTO.getProvinceName());
        assertEquals("파주시", secondDTO.getDistrictName());
        assertEquals("67890", secondDTO.getPostCode());
        assertEquals("캠핑장 특징을 요약한 글 2", secondDTO.getFeatureSummary());
        assertEquals("업종 2", secondDTO.getInduty());
        assertEquals("경기도 파주시", secondDTO.getAddr());
        assertEquals("상세주소2", secondDTO.getAddrDetails());
        assertEquals(59.602, secondDTO.getMapX());
        assertEquals(203.1, secondDTO.getMapY());
        assertEquals("042-5678-5678", secondDTO.getTel());
        assertEquals("http://www.camp2.com", secondDTO.getHomepage());
        assertEquals(2, secondDTO.getStaffCount());
    }

    private String createMockApiResponse() throws Exception {
        ObjectNode rootNode = objectMapper.createObjectNode();
        ObjectNode responseNode = objectMapper.createObjectNode();
        ObjectNode bodyNode = objectMapper.createObjectNode();
        ObjectNode itemsNode = objectMapper.createObjectNode();
        ArrayNode itemArray = objectMapper.createArrayNode();

        CampingDTO dto1 = CampingDTO.builder()
                .campName("Camp Site 1")
                .lineIntro("한줄 소개글 1")
                .intro("소개글 1")
                .provinceName("충청도")
                .districtName("단양군")
                .postCode("12345")
                .featureSummary("캠핑장 특징을 요약한 글")
                .induty("업종")
                .addr("충청도 단양군")
                .addrDetails("상세주소")
                .mapX(127.58)
                .mapY(99.021)
                .tel("042-1234-1234")
                .homepage("http://www.camp.com")
                .staffCount(2)
                .build();


        CampingDTO dto2 = CampingDTO.builder()
                .campName("Camp Site 2")
                .lineIntro("한줄 소개 2")
                .intro("소개글")
                .provinceName("경기도")
                .districtName("파주시")
                .postCode("67890")
                .featureSummary("캠핑장 특징을 요약한 글2")
                .induty("업종2")
                .addr("경기도 파주시")
                .addrDetails("상세주소2")
                .mapX(59.602)
                .mapY(203.1)
                .tel("031-5678-5678")
                .homepage("http://www.camp2.com")
                .staffCount(2)
                .build();

        itemArray.add(objectMapper.valueToTree(dto1));
        itemArray.add(objectMapper.valueToTree(dto2));

        itemsNode.set("item", itemArray);
        bodyNode.set("items", itemsNode);
        responseNode.set("body", bodyNode);
        rootNode.set("response", responseNode);

        return objectMapper.writeValueAsString(rootNode);
    }
}
