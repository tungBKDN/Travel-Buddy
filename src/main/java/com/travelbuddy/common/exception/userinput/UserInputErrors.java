package com.travelbuddy.common.exception.userinput;

import java.util.List;

public interface UserInputErrors {
    List<String> getErrorFields();

    List<String> getErrorMessages(String field);

    String getFirstErrorMessage(String field);

    boolean hasErrors(String field);
}
