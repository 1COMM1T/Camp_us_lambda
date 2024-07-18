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
    private String campName;
    private String lineIntro;
    private String intro;
    private String provinceName;
    private String districtName;
    private String postCode;
    private String featureSummary;
    private String induty;
    private String addr;
    private String addrDetails;
    private double mapX;
    private double mapY;
    private String tel;
    private String homepage;
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