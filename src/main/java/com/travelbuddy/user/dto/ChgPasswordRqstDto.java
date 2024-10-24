package com.travelbuddy.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChgPasswordRqstDto {
    private String oldPassword;

    private String newPassword;
}
