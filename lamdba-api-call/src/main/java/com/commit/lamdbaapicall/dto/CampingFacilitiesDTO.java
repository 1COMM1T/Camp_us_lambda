package com.commit.lamdbaapicall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampingFacilitiesDTO {
    private Long campFacsId;
    private Long campId;
    private int facsTypeId;
    @JsonProperty("sbrsCl")
    private String internalFacilitiesList; // 부대시설
    @JsonProperty("toiletCo")
    private int toiletCnt;
    @JsonProperty("swrmCo")
    private int showerRoomCnt;
    @JsonProperty("btrpCO")
    private int sinkCnt;
    @JsonProperty("braizerCl")
    private String brazierClass;
    @JsonProperty("featureNm")
    private String supportFacilities;
    @JsonProperty("posblFcltyCl")
    private String outdoorActivities;
    @JsonProperty("animalCmgCl")
    private String petAccess;
    @JsonProperty("firstImageUrl")
    private String firstImageUrl;
    @JsonProperty("operDeCl")
    private String operationDay;
    @JsonProperty("trlerAcmpnyAt")
    private String personalTrailerStatus;
    @JsonProperty("caravAcmpnyAt")
    private String personalCaravanStatus;
    @JsonProperty("eqpmnLendCl")
    private String rentalGearList;
    @JsonProperty("facltNm")
    private String facilityName;
}
