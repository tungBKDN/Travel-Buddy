package com.travelbuddy.common.exception.userinput;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserInputErrorsImpl implements UserInputErrors {
    private final Map<String, List<String>> errors;

    public UserInputErrorsImpl(List<InputErrorInfo> errors) {
        this.errors = errors.stream().collect(
                Collectors.groupingBy(InputErrorInfo::getField,
                        Collectors.mapping(InputErrorInfo::getMessage,
                                Collectors.toList())));
    }

    @Override
    public List<String> getErrorFields() {
        return errors.keySet().stream().toList();
    }

    @Override
    public List<String> getErrorMessages(String field) {
        return errors.get(field);
    }

    @Override
    public String getFirstErrorMessage(String field) {
        return errors.get(field).get(0);
    }

    @Override
    public boolean hasErrors(String field) {
        return errors.containsKey(field);
    }
}
