package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer> {

}
