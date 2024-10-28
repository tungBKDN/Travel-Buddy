package com.travelbuddy.persistence.domain.dto.site;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiteCreateRqstDto {
    private Integer ownerId;
    private String siteName;
    private double lat;
    private double lng;
    private String resolvedAddress;
    private String website;
    private Integer typeId;
    private List<String> phoneNumbers;
    private List<OpeningTimeCreateRqstDto> openingTimes;
}
