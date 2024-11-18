package com.travelbuddy.auth.controller;

import com.travelbuddy.auth.service.AdminAuthService;
import com.travelbuddy.persistence.domain.dto.auth.LoginRqstDto;
import com.travelbuddy.persistence.domain.dto.auth.LoginRspnDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody @Valid LoginRqstDto loginRqstDto) {
        LoginRspnDto loginRspnDto = adminAuthService.login(loginRqstDto);
        return ResponseEntity.ok(loginRspnDto);
    }
}
