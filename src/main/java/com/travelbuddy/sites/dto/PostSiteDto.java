package com.travelbuddy.sites.dto;

import com.travelbuddy.phoneNumbers.user.PhoneNumberEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostSiteDto {
    private Integer ownerID;
    private String siteName;
    private double lat;
    private double lon;
    private String resolvedAddress;
    private String website;
    private Integer typeID;
    private List<String> phoneNumbers;
}
