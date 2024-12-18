package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.dto.fee.CreateFeeRqstDto;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiteUpdateRqstDto {
    private Integer siteId;
    private String newSiteName;
    private double newLat;
    private double newLng;
    private String NewResolvedAddress;
    private String newWebSite;
    private Integer newTypeId;
    private List<String> newPhoneNumbers;
    private List<OpeningTimeCreateRqstDto> newOpeningTimes;
    private List<Integer> newServices;
    private List<CreateFeeRqstDto> fees;
    private String newDescription;
    private List<Integer> mediaIds;

    private List<FileRspnDto> newMedias;
}
