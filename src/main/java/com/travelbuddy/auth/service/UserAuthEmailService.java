package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.dto.auth.RegisterResponseDto;
import com.travelbuddy.persistence.domain.dto.auth.ResetPasswordRspnDto;

public interface UserAuthEmailService {
    void sendConfirmationEmail(RegisterResponseDto registerResponseDto);

    void sendResetPasswordEmail(ResetPasswordRspnDto build);
}
