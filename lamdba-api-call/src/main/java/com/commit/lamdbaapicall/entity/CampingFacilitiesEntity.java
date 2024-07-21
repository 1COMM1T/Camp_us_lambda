package com.commit.lamdbaapicall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camping_facilities")
public class CampingFacilitiesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campFacsId;
    private Long campId;
    private int facsTypeId;
    private String internalFacilitiesList;
    private int toiletCnt;
    private int showerRoomCnt;
    private int sinkCnt;
    private String brazierClass;
    private String supportFacilities;
    private String outdoorActivities;
    private String petAccess;
    private String firstImageUrl;
    private String operationDay;
    private String personalTrailerStatus;
    private String personalCaravanStatus;
    private String rentalGearList;
    private String facilityName;

}
