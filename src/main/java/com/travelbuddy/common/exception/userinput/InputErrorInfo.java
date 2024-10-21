package com.travelbuddy.common.exception.userinput;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputErrorInfo {
    private final String field;
    private final String message;
}
