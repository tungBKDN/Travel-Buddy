package com.travelbuddy.persistence.domain.dto.auth;

import com.travelbuddy.persistence.domain.dto.account.admin.AdminDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.account.user.UserDetailRspnDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminLoginRspnDto {
    private String accessToken;

    private String refreshToken;

    private AdminDetailRspnDto adminInfo;
}
