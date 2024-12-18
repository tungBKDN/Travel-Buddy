package com.travelbuddy.persistence.domain.dto.travelplan;

import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelPlanCreateRqstDto {
    @NotBlank
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private FileRspnDto cover;
}
