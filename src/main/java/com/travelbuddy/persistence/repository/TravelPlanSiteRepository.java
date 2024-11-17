package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.TravelPlanSiteEntity;
import com.travelbuddy.persistence.domain.entity.TravelPlanSiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TravelPlanSiteRepository extends JpaRepository<TravelPlanSiteEntity, TravelPlanSiteId> {
    @Query("SELECT tps FROM TravelPlanSiteEntity tps WHERE tps.id.travelPlanEntity.id = :travelPlanId AND tps.id.siteEntity.id = :siteId")
    Optional<TravelPlanSiteEntity> findByTravelPlanIdAndSiteId(Integer travelPlanId, Integer siteId);

    @Modifying
    @Query("DELETE FROM TravelPlanSiteEntity tps WHERE tps.id.travelPlanEntity.id = :travelPlanId AND tps.id.siteEntity.id = :siteId")
    void deleteByTravelPlanIdAndSiteId(int travelPlanId, int siteId);
}
