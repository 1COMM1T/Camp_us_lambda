package com.commit.lamdbaapicall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CampingDTO {
    private int campId;         // 캠핑장 ID
    private String facltNm;      // 야영장명
    private String lineIntro;   // 한줄소개
    private String intro;       // 소개
    private String doNm;        // 도
    private String sigunguNm;   // 시군구
    private String zipcode;     // 우편번호
    private String featureNm;   // 특징명
    private String induty;      // 업종
    private String addr1;       // 주소
    private String addr2;       // 주소 상세
    private double mapX;        // 경도
    private double mapY;        // 위도
    private String tel;         // 전화
    private String homepage;    // 홈페이지
    private int manageNmpr;     // 상주관리인원

    @Builder
    public CampingDTO(int campId, String facltNm, String lineIntro,
                      String intro, String doNm, String sigunguNm,
                      String zipcode,
                      String featureNm,
                      String induty,
                      String addr1, String addr2, double mapX,
                      double mapY, String tel, String homepage, int manageNmpr)
    {
        this.campId = campId;
        this.facltNm = facltNm;
        this.lineIntro = lineIntro;
        this.intro = intro;
        this.doNm = doNm;
        this.sigunguNm = sigunguNm;
        this.zipcode = zipcode;
        this.featureNm = featureNm;
        this.induty = induty;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.mapX = mapX;
        this.mapY = mapY;
        this.tel = tel;
        this.homepage = homepage;
        this.manageNmpr = manageNmpr;
    }
}
