package com.commit.lamdbaapicall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "camping")
@Getter
@Setter
@Service
@ToString  // 왜 쓰는지 알아보기
@NoArgsConstructor // 왜 쓰는지 알아보기
public class CampingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long campId;

    @Column(name = "camp_name", length = 100)
    private String campName;

    @Column(name = "line_intro", length = 255)
    private String lineIntro;

    @Column(name = "intro", columnDefinition = "MEDIUMTEXT")
    private String intro;

    @Column(name = "province_name", length = 50)
    private String doName;

    @Column(name = "district_name", length = 50)
    private String sigunguName;

    @Column(name = "post_code", length = 10)
    private String postCode;

    @Column(name = "feature_summary", columnDefinition = "MEDIUMTEXT")
    private String featureSummary;

    @Column(name = "induty", length = 50)
    private String induty;

    @Column(name = "addr", length = 100)
    private String addr;

    @Column(name = "addr_details", length = 100)
    private String addrDetails;

    @Column(name = "mapX")
    private double mapX;

    @Column(name = "mapY")
    private double mapY;

    @Column(name = "tel", length = 50)
    private String tel;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "staff_count")
    private int staffCount;

    @Column(name = "general_site_cnt")
    private int generalSiteCnt;

    @Column(name = "car_site_cnt")
    private int carSiteCnt;

    @Column(name = "glamping_site_cnt")
    private int glampingSiteCnt;

    @Column(name = "caravan_site_cnt")
    private int caravanSiteCnt;

    @Column(name = "personal_caravan_site_cnt")
    private int personalCaravanSiteCnt;

    @OneToMany(mappedBy = "campingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampingFacilitiesEntity> campingFacilities;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}