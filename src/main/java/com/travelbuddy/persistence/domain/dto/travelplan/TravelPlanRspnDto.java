package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelPlanRspnDto {
    private String name;
    private String description;
    private LocalDateTime startTime; // yyyyMMdd HH:mm
    private LocalDateTime endTime; // yyyyMMdd HH:mm
    private List<TravelPlanSiteRspnDto> sites;
    private List<TravelPlanMemberRspnDto> members;
}
