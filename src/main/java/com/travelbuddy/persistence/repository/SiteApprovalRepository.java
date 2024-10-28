package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteApprovalRepository extends JpaRepository<SiteApprovalEntity, Integer> {
}
