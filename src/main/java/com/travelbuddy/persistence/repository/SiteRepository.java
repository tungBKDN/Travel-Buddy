package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {
    @Modifying
    void deleteByOwnerId(Integer ownerId);
    Optional<SiteEntity> findById(Integer siteId);
}