package com.travelbuddy.siteTypes;


public enum DualState {
    ATTRACTION,
    UTILITY,
    DUAL;

    @Override
    public String toString() {
        switch (this) {
            case ATTRACTION:
                return "ATTRACTION";
            case UTILITY:
                return "UTILITY";
            case DUAL:
                return "DUAL";
            default:
                return "DUAL";
        }
    }
}
