package com.travelbuddy.persistence.domain.dto.travelplan;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TravelPlanMemberRqstDto {
    @NotNull
    private Integer userId;
}
