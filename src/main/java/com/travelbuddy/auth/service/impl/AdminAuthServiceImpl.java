package com.travelbuddy.auth.service.impl;

import com.travelbuddy.auth.token.jwt.JWTProcessor;
import com.travelbuddy.persistence.domain.dto.auth.AdminLoginRspnDto;
import com.travelbuddy.persistence.domain.entity.AdminEntity;
import com.travelbuddy.persistence.repository.AdminRepository;
import com.travelbuddy.auth.service.AdminAuthService;
import com.travelbuddy.mapper.AdminMapper;
import com.travelbuddy.persistence.domain.dto.auth.LoginRqstDto;
import com.travelbuddy.common.exception.auth.InvalidLoginCredentialsException;
import com.travelbuddy.persistence.domain.entity.GroupEntity;
import com.travelbuddy.persistence.domain.entity.PermissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {
    private final JWTProcessor jwtProcessor;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final AdminMapper adminMapper;

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

        return AdminLoginRspnDto.builder()
                .accessToken(accessToken)
                .adminInfo(adminMapper.toAdminDetailRspnDto(admin))
                .build();
    }
}
