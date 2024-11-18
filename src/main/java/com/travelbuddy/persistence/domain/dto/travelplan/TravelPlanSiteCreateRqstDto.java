package com.travelbuddy.persistence.domain.dto.travelplan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelPlanSiteCreateRqstDto {
    @NotNull
    private Integer siteId;
    @NotBlank
    private String name;
    private String description;
    private LocalDateTime startTime; // yyyyMMdd HH:mm
    private LocalDateTime endTime; // yyyyMMdd HH:mm
}
