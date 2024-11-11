package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer> {
    Optional<List<PhoneNumberEntity>> findAllBySiteVersionId(Integer siteVersionId);
}
