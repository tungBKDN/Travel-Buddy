package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.OpeningTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningTimeRepository extends JpaRepository<OpeningTimeEntity, Integer> {
}
