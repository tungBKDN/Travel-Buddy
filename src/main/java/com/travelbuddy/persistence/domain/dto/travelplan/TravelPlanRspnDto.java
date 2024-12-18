package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelPlanRspnDto {
    private String name;
    private String description;
    private String startTime; // yyyyMMdd HH:mm
    private String endTime; // yyyyMMdd HH:mm
    private String coverUrl;
    private List<TravelPlanSiteRspnDto> sites;
    private List<TravelPlanMemberRspnDto> members;
}
