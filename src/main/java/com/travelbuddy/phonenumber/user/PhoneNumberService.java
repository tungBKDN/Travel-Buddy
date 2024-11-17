package com.travelbuddy.phonenumber.user;

import com.travelbuddy.persistence.domain.entity.PhoneNumberEntity;

import java.util.List;

public interface PhoneNumberService {
    void addPhoneNumbers(List<PhoneNumberEntity> phoneNumbers);
    void addPhoneNumbers(List<String> phoneNumbers, Integer siteVersionId);
}
