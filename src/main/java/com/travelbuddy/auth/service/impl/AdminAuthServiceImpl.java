package com.travelbuddy.auth.service.impl;

import com.travelbuddy.auth.token.jwt.JWTProcessor;
import com.travelbuddy.persistence.domain.dto.auth.AdminLoginRspnDto;
import com.travelbuddy.persistence.domain.entity.AdminEntity;
import com.travelbuddy.persistence.repository.AdminRepository;
import com.travelbuddy.auth.service.AdminAuthService;
import com.travelbuddy.mapper.AdminMapper;
import com.travelbuddy.persistence.domain.dto.auth.LoginRqstDto;
import com.travelbuddy.persistence.domain.dto.auth.LoginRspnDto;
import com.travelbuddy.common.exception.auth.InvalidLoginCredentialsException;
import com.travelbuddy.persistence.domain.entity.GroupEntity;
import com.travelbuddy.persistence.domain.entity.PermissionEntity;
import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;
import com.travelbuddy.persistence.repository.TokenStoreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAuthServiceImpl implements AdminAuthService {
    private final JWTProcessor jwtProcessor;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenStoreRepository tokenStoreRepository;

    private final AdminMapper adminMapper;

    public AdminAuthServiceImpl(JWTProcessor jwtProcessor, AdminRepository adminRepository, PasswordEncoder passwordEncoder, TokenStoreRepository tokenStoreRepository, AdminMapper adminMapper) {
        this.jwtProcessor = jwtProcessor;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenStoreRepository = tokenStoreRepository;
        this.adminMapper = adminMapper;
    }

    public AdminLoginRspnDto login(LoginRqstDto loginRqstDto) {
        String email = loginRqstDto.getEmail();
        String password = loginRqstDto.getPassword();

        AdminEntity admin = adminRepository.findByEmail(email)
                .orElseThrow(InvalidLoginCredentialsException::new);

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new InvalidLoginCredentialsException();
        }

        List<String> authorities = new ArrayList<>();
        for (GroupEntity groupEntity : admin.getGroupEntities()) {
            authorities.add("ROLE_" + groupEntity.getName());
            authorities.addAll(groupEntity.getPermissionEntities().stream()
                    .map(PermissionEntity::getName)
                    .toList());
        }

        String accessToken = jwtProcessor.getBuilder()
                .withSubject(admin.getEmail())
                .withScopes(authorities)
                .build();

        String refreshToken = jwtProcessor.getBuilder()
                .withSubject(admin.getEmail())
                .build();

        tokenStoreRepository.save(TokenStoreEntity.builder()
                .token(refreshToken)
                .userId(admin.getId())
                .build());

        return AdminLoginRspnDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .adminInfo(adminMapper.toAdminDetailRspnDto(admin))
                .build();
    }
}
