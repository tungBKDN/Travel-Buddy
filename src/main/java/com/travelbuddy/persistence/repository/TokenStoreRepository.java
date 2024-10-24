package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenStoreRepository  extends JpaRepository<TokenStoreEntity, Integer> {
    Optional<TokenStoreEntity> findByToken(String Token);
    void deleteByToken(String Token);
}
