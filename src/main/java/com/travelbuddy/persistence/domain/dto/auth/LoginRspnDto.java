package com.travelbuddy.persistence.domain.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRspnDto {
    private String accessToken;

    private String refreshToken;

    private BasicInfoDto basicInfo;
}
