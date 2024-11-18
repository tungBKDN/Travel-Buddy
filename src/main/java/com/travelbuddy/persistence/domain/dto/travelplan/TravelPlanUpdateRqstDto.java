package com.travelbuddy.persistence.domain.dto.travelplan;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelPlanUpdateRqstDto {
    @NotBlank
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
