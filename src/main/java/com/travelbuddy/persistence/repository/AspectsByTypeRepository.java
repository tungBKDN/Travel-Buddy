package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspectsByTypeRepository extends JpaRepository<AspectsByTypeEntity, Integer> {
    boolean existsByTypeIdAndAspectNameIgnoreCase(Integer typeId, String aspectName);
    void deleteByTypeIdAndAspectNameIgnoreCase(Integer typeId, String aspectName);
}
