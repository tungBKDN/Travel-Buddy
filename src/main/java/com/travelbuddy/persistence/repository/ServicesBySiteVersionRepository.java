package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ServicesBySiteVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesBySiteVersionRepository extends JpaRepository<ServicesBySiteVersionEntity, Integer> {

    void deleteBySiteVersionIdAndServiceId(int siteVersionId, int serviceId);

    void deleteAllBySiteVersionId(int siteVersionId);

    Optional<List<ServicesBySiteVersionEntity>> findAllBySiteVersionId(int siteVersionId);
}
