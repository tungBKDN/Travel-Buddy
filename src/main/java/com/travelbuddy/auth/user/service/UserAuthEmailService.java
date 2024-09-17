package com.travelbuddy.auth.user.service;

import com.travelbuddy.auth.user.dto.RegisterTokenDto;
import com.travelbuddy.auth.user.dto.ResetPasswordTokenDto;

public interface UserAuthEmailService {
    void sendConfirmationEmail(RegisterTokenDto registerTokenDto);

    void sendResetPasswordEmail(ResetPasswordTokenDto build);
}
