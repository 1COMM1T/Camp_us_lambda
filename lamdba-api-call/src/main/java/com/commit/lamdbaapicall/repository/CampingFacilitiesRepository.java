package com.commit.lamdbaapicall.repository;

import com.commit.lamdbaapicall.entity.CampingFacilitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingFacilitiesRepository extends JpaRepository<CampingFacilitiesEntity, Long> {
}
