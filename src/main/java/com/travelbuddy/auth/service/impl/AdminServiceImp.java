package com.travelbuddy.auth.service.impl;

import com.travelbuddy.auth.service.AdminService;
import com.travelbuddy.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public int getAdminIdByEmailOrUsername(String emailOrUsername) {
        return adminRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"))
                .getId();
    }
}
