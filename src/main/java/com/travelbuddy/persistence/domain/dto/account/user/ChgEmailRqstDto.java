package com.travelbuddy.persistence.domain.dto.account.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChgEmailRqstDto {
    @NotBlank
    private String email;
}
