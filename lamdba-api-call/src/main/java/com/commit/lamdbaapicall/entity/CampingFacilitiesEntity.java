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

    @Column(name = "facs_type_id")
    private int facsTypeId;

    @Column(name = "internal_facilities_list")
    private String internalFacilitiesList;

    @Column(name = "toilet_cnt")
    private int toiletCnt;

    @Column(name = "shower_room_cnt")
    private int showerRoomCnt;

    @Column(name = "sink_cnt")
    private int sinkCnt;

    @Column(name = "brazier_class")
    private String brazierClass;

    @Column(name = "support_facilities")
    private String supportFacilities;

    @Column(name = "outdoor_activities")
    private String outdoorActivities;

    @Column(name = "pet_access")
    private String petAccess;

    @Column(name = "first_image_url")
    private String firstImageUrl;

    @Column(name = "operation_day")
    private String operationDay;

    @Column(name = "personal_trailer_status")
    private String personalTrailerStatus;

    @Column(name = "personal_caravan_status")
    private String personalCaravanStatus;

    @Column(name = "rental_gear_list")
    private String rentalGearList;

    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private CampingEntity campingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facs_type_id", insertable = false, updatable = false)
    private FacilityTypeEntity facilityTypeEntity;
}
