package com.travelbuddy.siteVersions.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SiteVersionRepository extends JpaRepository<SiteVersionEntity, Integer> {
}
