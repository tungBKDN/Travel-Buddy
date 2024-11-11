package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.common.constants.DayOfWeekEnum;
import com.travelbuddy.common.constants.MinimumOpenDuration;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
public class OpeningTimeCreateRqstDto {
    @NotNull
    private String dayOfWeek;

    @NotNull
    private LocalTime openTime;

    @NotNull
    private LocalTime closeTime;

    public Integer evaluateOpenTime() {
        DayOfWeekEnum dayOfWeek = DayOfWeekEnum.valueOf(this.dayOfWeek);
        return dayOfWeek.getValue() * 1140 + openTime.getHour() * 60 + openTime.getMinute();
    }

    public Integer evaluateCloseTime() {
        DayOfWeekEnum dayOfWeek = DayOfWeekEnum.valueOf(this.dayOfWeek);
        return dayOfWeek.getValue() * 1140 + closeTime.getHour() * 60 + closeTime.getMinute();
    }

    public boolean isCloseTimeBeforeOpenTime() {
        return evaluateCloseTime() < evaluateOpenTime() + MinimumOpenDuration.MINIMUM_OPEN_DURATION;
    }
}
