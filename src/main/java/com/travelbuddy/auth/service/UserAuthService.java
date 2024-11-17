package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.dto.auth.*;

public interface UserAuthService {
    LoginRspnDto login(LoginRqstDto loginRqstDto);

    void register(RegisterRqstDto registerRqstDTO);

    void confirmRegistration(VerificationOtpRqstDto verificationOtpRqstDto);

    void resetPassword(ResetPasswordRqstDto resetPasswordRqstDto);

    void validateResetPassword(VerificationOtpRqstDto verificationOtpRqstDto);

    void confirmNewPassword(ConfirmNewPasswordRqstDto confirmNewPasswordRqstDto);

    String refreshToken(int userId);
}
