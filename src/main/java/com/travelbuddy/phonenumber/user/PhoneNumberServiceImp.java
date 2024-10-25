package com.travelbuddy.phonenumber.user;

import com.travelbuddy.persistence.domain.entity.PhoneNumberEntity;
import com.travelbuddy.persistence.repository.PhoneNumberRepository;
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
    public void addPhoneNumbers(List<String> phoneNumbers, Integer siteVersionId) {
        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        for (String phoneNumber : phoneNumbers) {
            phoneNumberEntities.add(PhoneNumberEntity.builder()
                    .phoneNumber(phoneNumber)
                    .siteVersionId(siteVersionId)
                    .build());
        }
        phoneNumberRepository.saveAll(phoneNumberEntities);
    }
}
