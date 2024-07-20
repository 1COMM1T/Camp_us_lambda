package com.commit.lamdbaapicall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampingFacilitiesDTO {
    private int campingFacilityId;
    private String facilityName;
}
