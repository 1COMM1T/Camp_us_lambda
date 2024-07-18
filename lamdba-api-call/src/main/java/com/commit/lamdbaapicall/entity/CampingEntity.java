package com.commit.lamdbaapicall.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "camping")
@Getter    // 왜 쓰는지 알아보기
@ToString  // 왜 쓰는지 알아보기
@NoArgsConstructor // 왜 쓰는지 알아보기
public class CampingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campId;

    @Column(name = "camp_name", length = 100)
    private String campName;

    @Column(name = "line_intro", length = 255)
    private String lineIntro;

    @Column(name = "intro", columnDefinition = "TEXT")
    private String intro;

    @Column(name = "province_name", length = 50)
    private String provinceName;

    @Column(name = "district_name", length = 50)
    private String districtName;

    @Column(name = "post_code", length = 10)
    private String postCode;

    @Column(name = "feature_summary", columnDefinition = "TEXT")
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

    @Builder
    public CampingEntity(String campName, String lineIntro, String intro, String provinceName,
                         String districtName, String postCode,
                         String featureSummary,
                         String induty,
                         String addr, String addrDetails, double mapX, double mapY, String tel,
                         String homepage, int staffCount) {
        this.campName = campName;
        this.lineIntro = lineIntro;
        this.intro = intro;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.postCode = postCode;
        this.featureSummary = featureSummary;
        this.induty = induty;
        this.addr = addr;
        this.addrDetails = addrDetails;
        this.mapX = mapX;
        this.mapY = mapY;
        this.tel = tel;
        this.homepage = homepage;
        this.staffCount = staffCount;
    }
}