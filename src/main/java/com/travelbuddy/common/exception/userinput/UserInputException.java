package com.travelbuddy.common.exception.userinput;

import java.util.Collections;
import java.util.List;

public class UserInputException extends RuntimeException implements UserInputErrors{
    private final UserInputErrors errors;

    public UserInputException(String message) {
        super(message);
        this.errors = new UserInputErrorsImpl(Collections.emptyList());
    }

    public UserInputException(List<InputErrorInfo> errors) {
        this.errors = new UserInputErrorsImpl(errors);
    }

    @Override
    public List<String> getErrorFields() {
        return errors.getErrorFields();
    }

    @Override
    public List<String> getErrorMessages(String field) {
        return errors.getErrorMessages(field);
    }

    @Override
    public String getFirstErrorMessage(String field) {
        return errors.getFirstErrorMessage(field);
    }

    @Override
    public boolean hasErrors(String field) {
        return errors.hasErrors(field);
    }
}
