package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.dto.fee.CreateFeeRqstDto;
import com.travelbuddy.persistence.domain.entity.FeeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiteCreateRqstDto {
    private String siteName;
    private double lat;
    private double lng;
    private String resolvedAddress;
    private String website;
    private Integer typeId;
    private String description;
    private List<String> phoneNumbers;
    private List<OpeningTimeCreateRqstDto> openingTimes;
    private List<Integer> services;
    private List<CreateFeeRqstDto> fees;
}
