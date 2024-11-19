package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.dto.auth.AdminLoginRspnDto;
import com.travelbuddy.persistence.domain.dto.auth.LoginRqstDto;
import com.travelbuddy.persistence.domain.dto.auth.LoginRspnDto;

public interface AdminAuthService {
    public AdminLoginRspnDto login(LoginRqstDto loginRqstDto);
}
