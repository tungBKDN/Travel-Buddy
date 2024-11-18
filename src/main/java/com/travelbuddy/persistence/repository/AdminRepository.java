package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByEmail(String email);
}
