package com.travelbuddy.auth.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPasswordDto {
    @NotBlank
    private String newPassword;
}
