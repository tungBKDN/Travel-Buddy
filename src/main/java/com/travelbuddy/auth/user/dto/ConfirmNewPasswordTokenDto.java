package com.travelbuddy.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConfirmNewPasswordTokenDto {
    private Integer userId;

    private String token;

    private String newPassword;
}
