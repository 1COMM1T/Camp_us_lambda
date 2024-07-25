package com.commit.lamdbaapicall.repository;

import com.commit.lamdbaapicall.entity.CampingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingRepository extends JpaRepository<CampingEntity, Long> {
}
