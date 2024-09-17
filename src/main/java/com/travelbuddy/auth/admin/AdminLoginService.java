package com.travelbuddy.auth.admin;

import com.travelbuddy.auth.Role;
import com.travelbuddy.auth.dto.LoginDto;
import com.travelbuddy.auth.dto.ResponseToken;
import com.travelbuddy.auth.exception.InvalidLoginCredentialsException;
import com.travelbuddy.auth.token.jwt.JWTProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginService {
    private final JWTProcessor jwtProcessor;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminLoginService(JWTProcessor jwtProcessor, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.jwtProcessor = jwtProcessor;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseToken login(LoginDto loginDto) {
        return login(loginDto.getUsername(), loginDto.getPassword());
    }

    private ResponseToken login(String emailOrUsername, String password) {
        AdminEntity admin = adminRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(InvalidLoginCredentialsException::new);

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new InvalidLoginCredentialsException();
        }

        String roleName = admin.getRole() == 1 ? Role.ADMIN.name() : Role.MODERATOR.name();
        String token = jwtProcessor.getBuilder()
                .withSubject(admin.getEmail())
                .withScopes(List.of("ROLE_"+ (roleName)))
                .build();
        ResponseToken responseToken = new ResponseToken();
        responseToken.setAccessToken(token);

        return responseToken;
    }
}
