package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    boolean existsByEmailOrUsername(String email, String username);

    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.enabled = false AND u.createdAt < :thresholdDate")
    void deleteUnverifiedUsers(LocalDateTime thresholdDate);
}
