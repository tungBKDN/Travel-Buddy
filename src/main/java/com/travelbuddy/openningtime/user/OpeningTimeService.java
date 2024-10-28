package com.travelbuddy.openningtime.user;

import com.travelbuddy.persistence.domain.dto.site.OpeningTimeCreateRqstDto;

import java.util.List;

public interface OpeningTimeService {
    void addOpeningTimes(List<OpeningTimeCreateRqstDto> openingTimeCreateRqstDtoList, Integer siteVersionId);
}
