package com.travelbuddy.auth.user;

import com.travelbuddy.auth.dto.LoginDto;
import com.travelbuddy.auth.dto.ResponseToken;
import com.travelbuddy.auth.exception.InvalidLoginCredentialsException;
import com.travelbuddy.auth.user.dto.*;
import com.travelbuddy.common.exception.errorresponse.ErrorResponse;
import com.travelbuddy.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthController {
    private final UserAuthService userAuthService;

    private final UserRepository userRepository;

    public UserAuthController(UserAuthService userAuthService, UserRepository userRepository) {
        this.userAuthService = userAuthService;
        this.userRepository = userRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> postLogin(@RequestBody @Valid LoginDto loginDto) {
        try {
            ResponseToken responseToken = userAuthService.login(loginDto);
            return ResponseEntity.ok(responseToken);
        } catch (InvalidLoginCredentialsException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .withMessage("Invalid email or password").build());
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<Object> postRegister(@RequestBody @Valid RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .withMessage("Email already exists").build());
        }

        userAuthService.register(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/confirm-registration/{token}")
    public ResponseEntity<Void> confirmRegistration(@PathVariable("token") String verificationToken) {
        userAuthService.confirmRegistration(new VerificationTokenDto(verificationToken));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        userAuthService.resetPassword(resetPasswordDto);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/api/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam("userId") Integer userId, @RequestParam("token") String resetPasswordToken,
                                              @RequestBody @Valid NewPasswordDto newPasswordDto) {
        userAuthService.resetNewPassword(new ConfirmNewPasswordTokenDto(userId, resetPasswordToken, newPasswordDto.getNewPassword()));
        return ResponseEntity.noContent().build();
    }
}
