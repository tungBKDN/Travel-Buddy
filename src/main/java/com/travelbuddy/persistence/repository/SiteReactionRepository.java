package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteReactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteReactionRepository extends JpaRepository<SiteReactionEntity, Long> {
    Optional<SiteReactionEntity> findByUserIdAndSiteId(int userId, int siteId);

    int countBySiteIdAndReactionType(int siteId, String like);
}
