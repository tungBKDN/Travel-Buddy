package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPlanMemberRspnDto {
    private int userId;
    private String nickname;
    private String avatarUrl;
    private String role;
}
