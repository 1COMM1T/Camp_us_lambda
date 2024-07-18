package com.commit.lamdbaapicall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter    // 왜 쓰는지 알아보기
@ToString  // 왜 쓰는지 알아보기
@NoArgsConstructor // 왜 쓰는지 알아보기
public class CampingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campId;
    private String facltNm;
    private String lineIntro;
    private String intro;
    private String doNm;
    private String sigunguNm;
    private String zipcode;
    private String featureNm;
    private String induty;
    private String addr1;
    private String addr2;
    private double mapX;
    private double mapY;
    private String tel;
    private String homepage;
    private int manageNmpr;

    @Builder
    public CampingEntity(int campId, String facltNm, String lineIntro, String intro, String doNm,
                         String sigunguNm, String zipcode, String featureNm, String induty,
                         String addr1, String addr2, double mapX, double mapY, String tel,
                         String homepage, int manageNmpr) {
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