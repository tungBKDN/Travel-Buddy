package com.travelbuddy.common.constants;

public enum DayOfWeekEnum {
    MO(1),
    TU(2),
    WE(3),
    TH(4),
    FR(5),
    SA(6),
    SU(7);

    private final int value;

    DayOfWeekEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DayOfWeekEnum valueOf(int value) {
        for (DayOfWeekEnum dayOfWeekEnum : DayOfWeekEnum.values()) {
            if (dayOfWeekEnum.getValue() == value) {
                return dayOfWeekEnum;
            }
        }
        return null;
    }
}
