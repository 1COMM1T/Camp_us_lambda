package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.CampingDTO;
import com.commit.lamdbaapicall.dto.CampingFacilitiesDTO;
import com.commit.lamdbaapicall.dto.GoCampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;
import com.commit.lamdbaapicall.entity.FacilityTypeEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingFacilitiesRepository;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.commit.lamdbaapicall.repository.facilityTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CampingApiServiceImpl implements CampingApiService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;
    private final ObjectMapper objectMapper;
    private final CampingFacilitiesRepository campingFacilitiesRepository;
    private final com.commit.lamdbaapicall.repository.facilityTypeRepository facilityTypeRepository;

    @Value("${gocamping.api.encoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 0;
    private static final int PAGE_NO = 0;
    private static final String VALIDATION_CHECK_OS_KIND = "ETC";
    private static final String VALIDATION_CHECK_APP_NAME = "campus";
    private static final String RESPONSE_FIFE_FORMAT = "json";

    public CampingApiServiceImpl(CampingRepository campingRepository,
                                 CampingApiClient campingApiClient,
                                 ObjectMapper objectMapper, CampingFacilitiesRepository campingFacilitiesRepository, facilityTypeRepository facilityTypeRepository) {
        this.campingRepository = campingRepository;
        this.campingApiClient = campingApiClient;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.campingFacilitiesRepository = campingFacilitiesRepository;
        this.facilityTypeRepository = facilityTypeRepository;
    }

    // api 호출하여 json 가져오기
    @Override
    public String callCampingApi() {
        String responseJson =
                campingApiClient.getBaseList(
                        NUM_OF_ROWS, PAGE_NO,
                        VALIDATION_CHECK_OS_KIND,
                        VALIDATION_CHECK_APP_NAME,
                        serviceKey, RESPONSE_FIFE_FORMAT);
        ;

        log.info("responseJson: {}", responseJson.isEmpty());

        return responseJson;
    }

    @Transactional
    @Scheduled(cron = "${schedule.cron}") // 매일 자정에 실행
    @Override
    public void saveCampingData() {
        try {
            // 여기에 API 호출 및 JSON 데이터를 가져오는 로직을 추가합니다.
            String campingData = callCampingApi();

            JsonNode rootNode = objectMapper.readTree(campingData);
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<GoCampingDTO> goCampingDTOList = objectMapper.convertValue(itemsNode, new TypeReference<List<GoCampingDTO>>() {});

            for (GoCampingDTO campingDTO : goCampingDTOList) {
                CampingEntity campingEntity = new CampingEntity();
                campingEntity.setCampName(campingDTO.getCampName());
                campingEntity.setLineIntro(campingDTO.getLineIntro());
                campingEntity.setIntro(campingDTO.getIntro());
                campingEntity.setDoName(campingDTO.getDoName());
                campingEntity.setSigunguName(campingDTO.getSigunguName());
                campingEntity.setPostCode(campingDTO.getPostCode());
                campingEntity.setFeatureSummary(campingDTO.getFeatureSummary());
                campingEntity.setInduty(campingDTO.getInduty());
                campingEntity.setAddr(campingDTO.getAddr());
                campingEntity.setAddr(campingDTO.getAddrDetails());
                campingEntity.setMapX(campingDTO.getMapX());
                campingEntity.setMapX(campingDTO.getMapY());
                campingEntity.setTel(campingDTO.getTel());
                campingEntity.setHomepage(campingDTO.getHomepage());
                campingEntity.setStaffCount(campingDTO.getStaffCount());
                campingEntity.setCreatedDate(campingDTO.getCreatedDate());
                campingEntity.setLastModifiedDate(campingDTO.getModifiedDate());
                campingEntity.setGeneralSiteCnt(campingDTO.getGeneral_site_cnt());
                campingEntity.setCarSiteCnt(campingDTO.getCar_site_cnt());
                campingEntity.setGlampingSiteCnt(campingDTO.getGlamping_site_cnt());
                campingEntity.setCaravanSiteCnt(campingDTO.getCaravan_site_cnt());
                campingEntity.setPersonalCaravanSiteCnt(campingDTO.getPersonal_caravan_site_cnt());

                List<CampingFacilitiesEntity> facilities = new ArrayList<>();

                if (campingDTO.getGeneral_site_cnt() > 0) {
                    facilities.add(createFacility(campingEntity, campingDTO, 1)); // 1, 일반야영장
                }
                if (campingDTO.getCar_site_cnt() > 0) {
                    facilities.add(createFacility(campingEntity, campingDTO,1)); // 2, 자동차 야영장
                }
                if (campingDTO.getGlamping_site_cnt() > 0) {
                    facilities.add(createFacility(campingEntity, campingDTO,3)); // 3, 글램핑
                }
                if (campingDTO.getCar_site_cnt() > 0) {
                    facilities.add(createFacility(campingEntity, campingDTO,4)); // 4, 카라반
                }
                if (campingDTO.getPersonal_caravan_site_cnt() > 0) {
                    facilities.add(createFacility(campingEntity, campingDTO,1)); // 5, 개인카라반
                }

                campingEntity.setCampingFacilities(facilities);

                campingRepository.save(campingEntity);
                campingFacilitiesRepository.saveAll(facilities);

            }
        } catch (Exception e) {
            // 예외 처리 로직을 추가합니다.
            e.printStackTrace();
        }
    }

    private CampingFacilitiesEntity createFacility(CampingEntity camping, GoCampingDTO campingDTO, int facsTypeId) {
        FacilityTypeEntity facilityType = facilityTypeRepository.findById(facsTypeId).orElseThrow(() -> new RuntimeException("Facility type not found"));

        CampingFacilitiesEntity facilitiesEntity = new CampingFacilitiesEntity();
        facilitiesEntity.setCampingEntity(camping);
        facilitiesEntity.setInternalFacilitiesList(campingDTO.getInternalFacilitiesList());
        facilitiesEntity.setToiletCnt(campingDTO.getToiletCnt());
        facilitiesEntity.setShowerRoomCnt(campingDTO.getShowerRoomCnt());
        facilitiesEntity.setSinkCnt(campingDTO.getSinkCnt());
        facilitiesEntity.setBrazierClass(campingDTO.getBrazierClass());
        facilitiesEntity.setSupportFacilities(campingDTO.getSupportFacilities());
        facilitiesEntity.setOutdoorActivities(campingDTO.getOutdoorActivities());
        facilitiesEntity.setPetAccess(campingDTO.getPetAccess());
        facilitiesEntity.setFirstImageUrl(campingDTO.getFirstImageUrl());
        facilitiesEntity.setOperationDay(campingDTO.getOperationDay());
        facilitiesEntity.setPersonalTrailerStatus(campingDTO.getPersonalTrailerStatus());
        facilitiesEntity.setPersonalCaravanStatus(campingDTO.getPersonalCaravanStatus());
        facilitiesEntity.setRentalGearList(campingDTO.getRentalGearList());

        return facilitiesEntity;
    }
}


