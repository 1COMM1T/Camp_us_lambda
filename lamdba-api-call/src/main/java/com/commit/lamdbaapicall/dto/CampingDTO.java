package com.commit.lamdbaapicall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CampingDTO {
    private int campId;         // 캠핑장 ID
    private String campName;      // 야영장명
    private String lineIntro;   // 한줄소개
    private String intro;       // 소개
    private String provinceName;        // 도
    private String districtName;   // 시군구
    private String postCode;     // 우편번호
    private String featureSummary;   // 특징명
    private String induty;      // 업종
    private String addr;       // 주소
    private String addrDetails;       // 주소 상세
    private double mapX;        // 경도
    private double mapY;        // 위도
    private String tel;         // 전화
    private String homepage;    // 홈페이지
    private int staffCount;     // 상주관리인원

    @Builder
    public CampingDTO(int campId, String campName, String lineIntro,
                      String intro, String provinceName, String districtName,
                      String postCode,
                      String featureSummary,
                      String induty,
                      String addr, String addrDetails, double mapX,
                      double mapY, String tel, String homepage, int staffCount)
    {
        this.campId = campId;
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
