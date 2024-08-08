package com.commit.lamdbaapicall.repository;

import com.commit.lamdbaapicall.entity.FacilityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface facilityTypeRepository extends JpaRepository<FacilityTypeEntity, Integer> {

}
