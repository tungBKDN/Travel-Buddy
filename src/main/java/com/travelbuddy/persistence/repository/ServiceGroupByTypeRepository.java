package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServiceGroupByTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceGroupByTypeRepository extends JpaRepository<ServiceGroupByTypeEntity, Integer> {
    Optional<List<ServiceGroupByTypeEntity>> findAllByTypeId(Integer typeId);
    Optional<ServiceGroupByTypeEntity> findByTypeIdAndServiceGroupId(Integer typeId, Integer serviceGroupId);
}
