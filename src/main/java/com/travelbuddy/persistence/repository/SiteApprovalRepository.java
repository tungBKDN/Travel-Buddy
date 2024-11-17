package com.travelbuddy.persistence.repository;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SiteApprovalRepository extends JpaRepository<SiteApprovalEntity, Integer> {
    // Get the latest (approve_at) siteVersionId that status is APPROVED
    Optional<SiteApprovalEntity> findTopBySiteVersionIdAndStatusOrderByApprovedAtDesc(Integer siteVersionId, ApprovalStatusEnum status);

    @Query("SELECT sa.siteVersionId FROM SiteApprovalEntity sa " +
            "JOIN SiteVersionEntity sv ON sa.siteVersionId = sv.id " +
            "WHERE sa.status = 'APPROVED' " +
            "AND sv.siteId = :siteId " +
            "ORDER BY sv.createdAt DESC")
    Optional<Integer> findLatestApprovedSiteVersionIdBySiteId(@Param("siteId") Integer siteId);
}
