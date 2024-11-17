package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.common.constants.DayOfWeekEnum;
import com.travelbuddy.persistence.domain.entity.OpeningTimeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class OpeningTimeRepresentationDto {
    private DayOfWeekEnum dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;

    public OpeningTimeRepresentationDto(OpeningTimeEntity openingTimeEntity) {
        this.dayOfWeek = openingTimeEntity.getDayOfWeek();
        this.openTime = openingTimeEntity.getOpenTime();
        this.closeTime = openingTimeEntity.getCloseTime();
    }
}
