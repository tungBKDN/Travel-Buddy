package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.domain.dto.auth.*;

public interface UserAuthService {
    LoginRspnDto login(LoginRqstDto loginRqstDto);

    void register(RegisterRqstDto registerRqstDTO);

    void confirmRegistration(VerificationOtpRqstDto verificationOtpRqstDto);

    void resetPassword(ResetPasswordRqstDto resetPasswordRqstDto);

    void confirmNewPassword(ConfirmNewPasswordRqstDto confirmNewPasswordRqstDto);

    BasicInfoDto getUserBasicInfo(String emailOrUsername);

    String refreshToken(int userId);
}
