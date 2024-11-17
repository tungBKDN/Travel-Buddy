package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class TravelPlanUserId implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_plan_id", referencedColumnName = "id")
    private TravelPlanEntity travelPlanEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}
