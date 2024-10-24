package com.travelbuddy.auth.controller;

import com.travelbuddy.common.exception.auth.InvalidLoginCredentialsException;
import com.travelbuddy.auth.service.TokenStoreService;
import com.travelbuddy.auth.service.UserAuthService;
import com.travelbuddy.persistence.domain.dto.auth.*;
import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;
import com.travelbuddy.common.exception.errorresponse.ErrorResponse;
import com.travelbuddy.user.UserService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    private final UserAuthService userAuthService;

    private final UserService userService;

    private final TokenStoreService tokenStoreService;

    public UserAuthController(UserAuthService userAuthService, UserService userService, TokenStoreService tokenStoreService) {
        this.userAuthService = userAuthService;
        this.userService = userService;
        this.tokenStoreService = tokenStoreService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody @Valid LoginRqstDto loginRqstDto) {
        try {
            LoginRspnDto loginRspnDto = userAuthService.login(loginRqstDto);
            return ResponseEntity.ok(loginRspnDto);
        } catch (InvalidLoginCredentialsException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .withMessage("Invalid email or password").build());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postRegister(@RequestBody @Valid RegisterRqstDto registerRqstDto) {
        if(userService.isUserExists(registerRqstDto.getEmail())) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .withMessage("Email already exists").build());
        }

        userAuthService.register(registerRqstDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/confirm-registration")
    public ResponseEntity<Void> confirmRegistration(@RequestBody @Valid VerificationOtpRqstDto verificationOtpRqstToken) {
        userAuthService.confirmRegistration(verificationOtpRqstToken);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRqstDto resetPasswordRqstDto) {
        userAuthService.resetPassword(resetPasswordRqstDto);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ConfirmNewPasswordRqstDto confirmNewPasswordRqstDto) {
        userAuthService.confirmNewPassword(confirmNewPasswordRqstDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        tokenStoreService.deleteToken(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRspnDto> refreshToken(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        Optional<TokenStoreEntity> tokenStoreEntity = tokenStoreService.findByToken(token.substring(7));

        if(tokenStoreEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String accessToken = userAuthService.refreshToken(tokenStoreEntity.get().getUserId());

        return ResponseEntity.ok(TokenRspnDto.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build());
    }
}
