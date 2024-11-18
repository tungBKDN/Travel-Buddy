package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SiteReviewRepository extends JpaRepository<SiteReviewEntity, Long>, JpaSpecificationExecutor<SiteReviewEntity> {
    @Query("SELECT AVG(sre.generalRating) FROM SiteReviewEntity sre WHERE sre.siteId = ?1")
    Double getAverageGeneralRatingBySiteId(Integer siteId);

    @Query("SELECT COUNT(sre) FROM SiteReviewEntity sre WHERE sre.siteId = ?1")
    Integer countBySiteId(Integer siteId);

    @Query("SELECT COUNT(sre) FROM SiteReviewEntity sre WHERE sre.siteId = ?1 AND sre.generalRating = ?2")
    Integer countBySiteIdAndGeneralRating(Integer siteId, int i);
}
