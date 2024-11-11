package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    boolean existsByServiceNameIgnoreCase(String serviceName);

    Page<ServiceEntity> searchServiceEntitiesByServiceNameContainingIgnoreCase(String serviceSearch, Pageable pageable);
}
