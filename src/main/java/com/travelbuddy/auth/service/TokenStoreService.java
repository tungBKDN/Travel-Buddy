package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;

import java.util.Optional;

public interface TokenStoreService {
    void saveToken(String token, int userId);

    Optional<TokenStoreEntity> findByToken(String Token);

    void deleteToken(String token);
}
