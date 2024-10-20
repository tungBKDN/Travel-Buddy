package com.travelbuddy.phoneNumbers.user;

import java.util.List;

public interface PhoneNumberService {
    void addPhoneNumbers(List<PhoneNumberEntity> phoneNumbers);
    void addPhoneNumbers(List<String> phoneNumbers, Integer siteVersionID);
}
