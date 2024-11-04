package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
//    boolean existedByServiceNameIgnoreCase(String serviceName);
}
