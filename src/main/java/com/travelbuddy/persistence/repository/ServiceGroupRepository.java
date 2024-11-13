package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServiceGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceGroupRepository extends JpaRepository<ServiceGroupEntity, Integer> {
    boolean existsByServiceGroupNameIgnoreCase(String serviceGroupName);
}
