package com.commit.lamdbaapicall.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "camping_facilities")
public class CampingFacilitiesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campFacsId;

}
