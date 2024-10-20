package com.travelbuddy.phoneNumbers.user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberServiceImp implements PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberServiceImp(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public void addPhoneNumbers(List<PhoneNumberEntity> phoneNumbers) {
        phoneNumberRepository.saveAll(phoneNumbers);
    }

    @Transactional
    @Override
    public void addPhoneNumbers(List<String> phoneNumbers, Integer siteVersionID) {
        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        for (String phoneNumber : phoneNumbers) {
            PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
            phoneNumberEntity.setPhoneNumber(phoneNumber);
            phoneNumberEntity.setSiteVersionID(siteVersionID);
            phoneNumberEntities.add(phoneNumberEntity);
        }
        phoneNumberRepository.saveAll(phoneNumberEntities);
    }
}
