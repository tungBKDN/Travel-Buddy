package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.dto.auth.LoginRqstDto;
import com.travelbuddy.persistence.domain.dto.auth.LoginRspnDto;

public interface AdminAuthService {
    public LoginRspnDto login(LoginRqstDto loginRqstDto);
}
