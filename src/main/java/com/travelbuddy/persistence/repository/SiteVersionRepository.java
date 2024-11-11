package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVersionRepository extends JpaRepository<SiteVersionEntity, Integer> {
    // get site type from site version id
    Integer findTypeIdById(Integer id);
    Optional<SiteVersionEntity> findById(Integer id);
}
