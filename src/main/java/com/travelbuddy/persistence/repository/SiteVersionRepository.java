package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteVersionRepository extends JpaRepository<SiteVersionEntity, Integer> {
}
