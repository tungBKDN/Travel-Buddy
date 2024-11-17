package com.travelbuddy.persistence.domain.dto.travelplan;

import com.travelbuddy.persistence.domain.dto.site.SiteBasicInfoRspnDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPlanSiteRspnDto {
    private String name;
    private String description;
    private LocalDateTime startTime; // yyyyMMdd HH:mm
    private LocalDateTime endTime; // yyyyMMdd HH:mm
    private SiteBasicInfoRspnDto siteBasicInfoRspnDto;
}
