package com.travelbuddy.persistence.domain.dto.account.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChgPasswordRqstDto {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
