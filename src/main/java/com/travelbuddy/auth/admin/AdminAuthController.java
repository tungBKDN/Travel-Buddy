package com.travelbuddy.auth.admin;

import com.travelbuddy.auth.dto.LoginDto;
import com.travelbuddy.auth.dto.ResponseToken;
import com.travelbuddy.auth.exception.InvalidLoginCredentialsException;
import com.travelbuddy.common.exception.errorresponse.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminAuthController {
    private final AdminLoginService adminLoginService;

    public AdminAuthController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/admin/api/login")
    public ResponseEntity<Object> postLogin(@RequestBody @Valid LoginDto loginDto) {
        try {
            ResponseToken responseToken = adminLoginService.login(loginDto);
            return ResponseEntity.ok(responseToken);
        } catch (InvalidLoginCredentialsException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                    .withMessage("Invalid email or password").build());
        }
    }
}
