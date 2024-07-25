package com.commit.lamdbaapicall.service;

import com.commit.lamdbaapicall.dto.GoCampingDTO;
import com.commit.lamdbaapicall.entity.CampingEntity;
import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;
import com.commit.lamdbaapicall.openfeign.CampingApiClient;
import com.commit.lamdbaapicall.repository.CampingFacilitiesRepository;
import com.commit.lamdbaapicall.repository.CampingRepository;
import com.commit.lamdbaapicall.repository.facilityTypeRepository;
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
import java.util.List;

@Service
@Slf4j
public class CampingApiServiceImpl implements CampingApiService {

    private final CampingRepository campingRepository;
    private final CampingApiClient campingApiClient;
    private final ObjectMapper objectMapper;
    private final CampingFacilitiesRepository campingFacilitiesRepository;

    @Value("${gocamping.api.encoding-key}")
    private String serviceKey;

    private static final int NUM_OF_ROWS = 5000;
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

        log.info("responseJson: {}", responseJson.isEmpty());

        return responseJson;
    }

    @Transactional
    @Scheduled(cron = "${schedule.cron}") // 매일 자정에 실행
    @Override
    public void saveCampingData() {
        try {
            String campingData = callCampingApi();

            JsonNode rootNode = objectMapper.readTree(campingData);
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<GoCampingDTO> campingDTOList = objectMapper.convertValue(itemsNode, new TypeReference<List<GoCampingDTO>>() {});

            for (GoCampingDTO campingDTO : campingDTOList) {

                CampingEntity campingEntity = mapToEntity(campingDTO);
                List<CampingFacilitiesEntity> facilities = checkCampFacsType(campingEntity, campingDTO);

                campingEntity.setCampingFacilities(facilities);

                // 엔티티 저장 또는 업데이트
                campingRepository.save(campingEntity);
                campingFacilitiesRepository.saveAll(facilities);

                log.info("Saved/Updated camp '{}'.", campingDTO.getCampName());
            }
        } catch (Exception e) {
            log.error("Error while saving camping data", e);
        }
    }

    private CampingEntity mapToEntity(GoCampingDTO campingDTO) {
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
        campingEntity.setMapY(campingDTO.getMapY());
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
        campingEntity.setSupportFacilities(campingDTO.getSupportFacilities());
        campingEntity.setOutdoorActivities(campingDTO.getOutdoorActivities());
        campingEntity.setPetAccess(campingDTO.getPetAccess());

        return campingEntity;
    }
    private List<CampingFacilitiesEntity> checkCampFacsType(CampingEntity campingEntity, GoCampingDTO campingDTO) {
        List<CampingFacilitiesEntity> facilities = new ArrayList<>();

        if (campingDTO.getGeneral_site_cnt() > 0) {
            facilities.add(
                    createFacility(campingEntity, campingDTO, 1)); // 1, 일반야영장
        }
        if (campingDTO.getCar_site_cnt() > 0) {
            facilities.add(
                    createFacility(campingEntity, campingDTO, 2)); // 2, 자동차 야영장
        }
        if (campingDTO.getGlamping_site_cnt() > 0) {
            facilities.add(
                    createFacility(campingEntity, campingDTO, 3)); // 3, 글램핑
        }
        if (campingDTO.getCaravan_site_cnt() > 0) {
            facilities.add(
                    createFacility(campingEntity, campingDTO, 4)); // 4, 카라반
        }
        if (campingDTO.getPersonal_caravan_site_cnt() > 0) {
            facilities.add(
                    createFacility(campingEntity, campingDTO, 5)); // 5, 개인카라반
        }

        return facilities;
    }

    private CampingFacilitiesEntity createFacility(CampingEntity camping, GoCampingDTO campingDTO, int facsTypeId) {

        CampingFacilitiesEntity facilitiesEntity = new CampingFacilitiesEntity();

        facilitiesEntity.setCampingEntity(camping);
        facilitiesEntity.setInternalFacilitiesList(campingDTO.getInternalFacilitiesList());
        facilitiesEntity.setToiletCnt(campingDTO.getToiletCnt());
        facilitiesEntity.setShowerRoomCnt(campingDTO.getShowerRoomCnt());
        facilitiesEntity.setSinkCnt(campingDTO.getSinkCnt());
        facilitiesEntity.setBrazierClass(campingDTO.getBrazierClass());
        facilitiesEntity.setFirstImageUrl(campingDTO.getFirstImageUrl());
//        facilitiesEntity.setOperationDay(campingDTO.getOperationDay());
        facilitiesEntity.setPersonalTrailerStatus(campingDTO.getPersonalTrailerStatus());
        facilitiesEntity.setPersonalCaravanStatus(campingDTO.getPersonalCaravanStatus());
//        facilitiesEntity.setRentalGearList(campingDTO.getRentalGearList());
        facilitiesEntity.setFacsTypeId(facsTypeId);

        return facilitiesEntity;
    }
}