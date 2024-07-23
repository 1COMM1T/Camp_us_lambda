package com.commit.lamdbaapicall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampingFacilitiesDTO {
    private Long campFacsId;
    private Long campId;
    private int facsTypeId;

    @JsonProperty("glampInnerFclty")
    private String internalFacilitiesList; // 내부시설

    @JsonProperty("toiletCo")
    private int toiletCnt;

    @JsonProperty("swrmCo")
    private int showerRoomCnt;

    @JsonProperty("btrpCO")
    private int sinkCnt;

    @JsonProperty("braizerCl")
    private String brazierClass;

    @JsonProperty("sbrsCl")
    private String supportFacilities;   // 부대시설

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
}
