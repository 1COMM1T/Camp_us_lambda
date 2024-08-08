package com.commit.lamdbaapicall.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeDTO {
    private int facsTypeId;
    private String facility_name;
    private int capacity;
}
