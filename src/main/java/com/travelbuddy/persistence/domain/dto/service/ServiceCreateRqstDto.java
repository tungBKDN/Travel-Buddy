package com.travelbuddy.persistence.domain.dto.service;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCreateRqstDto {
    @NotNull
    private String serviceName;
}
