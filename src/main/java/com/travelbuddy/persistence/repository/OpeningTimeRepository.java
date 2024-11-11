package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.OpeningTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpeningTimeRepository extends JpaRepository<OpeningTimeEntity, Integer> {
    Optional<List<OpeningTimeEntity>> findAllBySiteVersionId(Integer siteVersionId);
}
