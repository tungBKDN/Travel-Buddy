package com.travelbuddy.persistence.domain.dto.account.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailUpdateRqstDto {
    private String gender;
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;
    private String socialUrl;
}
