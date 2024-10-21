package com.travelbuddy.auth.admin;

import com.travelbuddy.auth.dto.LoginRequestDto;
import com.travelbuddy.auth.dto.ResponseToken;
import com.travelbuddy.auth.exception.InvalidLoginCredentialsException;
import com.travelbuddy.auth.permission.GroupEntity;
import com.travelbuddy.auth.permission.PermissionEntity;
import com.travelbuddy.auth.token.jwt.JWTProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAuthService {
    private final JWTProcessor jwtProcessor;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminAuthService(JWTProcessor jwtProcessor, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.jwtProcessor = jwtProcessor;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseToken login(LoginRequestDto loginRequestDto) {
        return login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
    }

    private ResponseToken login(String email, String password) {
        AdminEntity admin = adminRepository.findByEmailOrUsername(email, email)
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
        
        String token = jwtProcessor.getBuilder()
                .withSubject(admin.getEmail())
                .withScopes(authorities)
                .build();
        ResponseToken responseToken = new ResponseToken();
        responseToken.setAccessToken(token);

        return responseToken;
    }
}
