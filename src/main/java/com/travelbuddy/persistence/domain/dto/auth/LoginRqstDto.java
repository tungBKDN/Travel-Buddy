package com.travelbuddy.persistence.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRqstDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
