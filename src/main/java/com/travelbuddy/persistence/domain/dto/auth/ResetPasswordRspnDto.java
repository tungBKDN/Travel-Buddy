package com.travelbuddy.persistence.domain.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordRspnDto {
    private String fullName;

    private String email;

    private String otp;
}
