package com.travelbuddy.auth.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordTokenDto {
    private Integer userId;

    private String name;

    private String email;

    private String token;
}
