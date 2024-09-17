package com.travelbuddy.common.fileresource;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaType {
    IMAGE("image"),
    VIDEO("video"),
    AUDIO("audio"),
    RAW("raw");

    private final String type;

    MediaType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
