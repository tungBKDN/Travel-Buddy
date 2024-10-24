package com.travelbuddy.auth.service.impl;

import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;
import com.travelbuddy.persistence.repository.TokenStoreRepository;
import com.travelbuddy.auth.service.TokenStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenStoreServiceImpl implements TokenStoreService {
    private final TokenStoreRepository tokenStoreRepository;

    @Override
    public void saveToken(String token, int userId) {
        TokenStoreEntity tokenStoreEntity = TokenStoreEntity.builder()
                .token(token)
                .userId(userId)
                .build();
        tokenStoreRepository.save(tokenStoreEntity);
    }

    @Override
    public Optional<TokenStoreEntity> findByToken(String token) {
        return tokenStoreRepository.findByToken(token);
    }

    @Override
    public void deleteToken(String token) {
        tokenStoreRepository.deleteByToken(token);
    }
}
