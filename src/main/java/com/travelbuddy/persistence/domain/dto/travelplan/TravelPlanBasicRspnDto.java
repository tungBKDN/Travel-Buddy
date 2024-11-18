package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlanBasicRspnDto {
    private Integer id;
    private String name;
    private String description;
    private String startTime; // yyyyMMdd HH:mm
    private String endTime; // yyyyMMdd HH:mm
}
