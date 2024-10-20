package com.travelbuddy.sites.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.travelbuddy.sites.user.SiteEntity;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {
    @Modifying
    @Query("DELETE FROM SiteEntity s WHERE s.ownerId = :ownerId")
    void deleteByOwnerId(Long ownerId);
}