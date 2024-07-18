package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.repository.CampingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class CampingApiServiceImplTest {

    @Autowired
    private CampingApiService campingApiService;

    @Autowired
    private CampingRepository campingRepository;

    @Test
    public void testCallCampingApi() {
        String response = campingApiService.callCampingApi();
        assertNotNull(response);
        System.out.println("Camping API Response: " + response);
    }

    @Test
    public void testParseJsonToDTOList() {
        List<CampingDTO> campingDTOList = campingApiService.parseJsonToDTOList();
        assertFalse(campingDTOList.isEmpty());
        campingDTOList.forEach(dto -> System.out.println("CampingDTO: " + dto));
    }

    @Test
    public void testConvertDTOToEntity() {
        List<CampingDTO> campingDTOList = campingApiService.parseJsonToDTOList();
        campingDTOList.forEach(dto -> {
            CampingEntity entity = campingApiService.convertDTOToEntity(dto);
            assertNotNull(entity);
            System.out.println("CampingEntity: " + entity);
        });
    }

    @Test
    public void testSaveToDatabase() {
        List<CampingDTO> campingDTOList = campingApiService.parseJsonToDTOList();
        campingApiService.saveToDatabase(campingDTOList);

        // 저장 후 데이터베이스 상태를 확인
        List<CampingEntity> savedEntities = campingRepository.findAll();
        assertFalse(savedEntities.isEmpty());
        savedEntities.forEach(entity -> System.out.println("Saved CampingEntity: " + entity));
    }
}

