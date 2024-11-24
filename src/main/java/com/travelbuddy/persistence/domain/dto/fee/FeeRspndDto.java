package com.travelbuddy.persistence.domain.dto.fee;

import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import com.travelbuddy.persistence.domain.entity.FeeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeeRspndDto {
    private Integer id;
    private AspectsByTypeEntity aspect;
    private Integer feeLow;
    private Integer feeHigh;

    public FeeRspndDto() {
    }

    public FeeRspndDto(Integer id, AspectsByTypeEntity aspect, Integer feeLow, Integer feeHigh) {
        this.id = id;
        this.aspect = aspect;
        this.feeLow = feeLow;
        this.feeHigh = feeHigh;
    }

    public FeeRspndDto(FeeEntity feeEntity, AspectsByTypeEntity aspect) {
        this.id = feeEntity.getId();
        this.aspect = aspect;
        this.feeLow = feeEntity.getFeeLow();
        this.feeHigh = feeEntity.getFeeHigh();
    }
}
