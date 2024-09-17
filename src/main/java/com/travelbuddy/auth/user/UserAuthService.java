package com.travelbuddy.auth.user;

import com.travelbuddy.auth.dto.LoginDto;
import com.travelbuddy.auth.dto.ResponseToken;
import com.travelbuddy.auth.user.dto.ConfirmNewPasswordTokenDto;
import com.travelbuddy.auth.user.dto.RegisterDto;
import com.travelbuddy.auth.user.dto.ResetPasswordDto;
import com.travelbuddy.auth.user.dto.VerificationTokenDto;

public interface UserAuthService {
    ResponseToken login(LoginDto loginDto);

    void register(RegisterDto registerDTO);

    void confirmRegistration(VerificationTokenDto verificationTokenDto);

    void resetPassword(ResetPasswordDto resetPasswordDto);

    void resetNewPassword(ConfirmNewPasswordTokenDto confirmNewPasswordTokenDto);
}
