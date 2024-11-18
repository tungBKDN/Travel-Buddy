package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
@Builder
public class TravelPlanSiteId implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_plan_id", referencedColumnName = "id")
    private TravelPlanEntity travelPlanEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private SiteEntity siteEntity;
}
