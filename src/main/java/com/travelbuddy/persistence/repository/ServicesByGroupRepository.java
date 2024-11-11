package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServicesByGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServicesByGroupRepository extends JpaRepository<ServicesByGroupEntity, Integer> {
    Optional<List<ServicesByGroupEntity>> findAllByServiceGroupIdAndServiceIdIn(int groupId, List<Integer> serviceIds);
}
