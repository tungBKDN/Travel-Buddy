package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelPlanSiteUpdateRqstDto {
    private int siteId;
    private String name;
    private String description;
    private LocalDateTime startTime; // yyyyMMdd HH:mm
    private LocalDateTime endTime; // yyyyMMdd HH:mm
}
