package com.travelbuddy.persistence.repository;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteApprovalRepository extends JpaRepository<SiteApprovalEntity, Integer> {
    // Get the latest (approve_at) siteVersionId that status is APPROVED
    Optional<SiteApprovalEntity> findTopBySiteVersionIdAndStatusOrderByApprovedAtDesc(Integer siteVersionId, ApprovalStatusEnum status);
}
