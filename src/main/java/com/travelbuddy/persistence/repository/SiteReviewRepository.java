package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SiteReviewRepository extends JpaRepository<SiteReviewEntity, Long>, JpaSpecificationExecutor<SiteReviewEntity> {
}
