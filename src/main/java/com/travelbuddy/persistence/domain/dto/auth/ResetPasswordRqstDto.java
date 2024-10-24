package com.travelbuddy.persistence.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRqstDto {
    @NotBlank(message = "Email is required")
    private String email;
}
