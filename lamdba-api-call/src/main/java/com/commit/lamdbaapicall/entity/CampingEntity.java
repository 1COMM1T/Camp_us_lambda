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

    // 빌더 사용 시 아래 예제 참고
//    @Builder
//    public CampingEntity(int campId, String facltNm) {
//        this.campId = campId;
//        this.facltNm = facltNm;
//    }
}
