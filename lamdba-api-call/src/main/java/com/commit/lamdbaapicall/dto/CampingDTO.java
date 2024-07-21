package com.commit.lamdbaapicall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampingDTO {
    private int campId;         // 캠핑장 ID

    @JsonProperty("facltNm")
    private String campName;  // 야영장명

    @JsonProperty("lineIntro")
    private String lineIntro;  // 한줄소개

    @JsonProperty("intro")
    private String intro;  // 소개

    @JsonProperty("doNm")
    private String doName;  // 도

    @JsonProperty("sigunguNm")
    private String sigunguName;  // 시군구

    @JsonProperty("zipcode")
    private String postCode;  // 우편번호

    @JsonProperty("featureNm")
    private String featureSummary;  // 특징명

    @JsonProperty("induty")
    private String induty;  // 업종

    @JsonProperty("addr1")
    private String addr;  // 주소

    @JsonProperty("addr2")
    private String addrDetails;  // 주소 상세

    @JsonProperty("mapX")
    private double mapX;  // 경도

    @JsonProperty("mapY")
    private double mapY;  // 위도

    @JsonProperty("tel")
    private String tel;  // 전화

    @JsonProperty("homepage")
    private String homepage;  // 홈페이지

    @JsonProperty("manageNmpr")
    private int staffCount;  // 상주관리인원

    @JsonProperty("createdtime")
    private LocalDateTime createdDate;  // 등록일

    @JsonProperty("modifiedtime")
    private LocalDateTime modifiedDate; // 수정일
}
