package com.commit.lamdbaapicall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facility_type")
public class FacilityTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facs_type_id")
    private int facsTypeId;

    @Column(name = "facility_name")
    private String facility_name;

    @Column(name = "capacity")
    private int capacity;
}
