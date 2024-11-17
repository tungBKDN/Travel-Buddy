package com.travelbuddy.persistence.domain.dto.sitetype;

import com.travelbuddy.common.constants.DualStateEnum;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SiteTypeRspnDto {
    private int id;
    private String name;
    private boolean isAttraction;
    private boolean isAmenity;

    public SiteTypeRspnDto(SiteTypeEntity siteTypeEntity) {
        this.id = siteTypeEntity.getId();
        this.name = siteTypeEntity.getTypeName();
        this.isAttraction = (siteTypeEntity.getDualState() == DualStateEnum.ATTRACTION || siteTypeEntity.getDualState() == DualStateEnum.DUAL);
        this.isAmenity = (siteTypeEntity.getDualState() == DualStateEnum.AMENITY || siteTypeEntity.getDualState() == DualStateEnum.DUAL);
    }
}
