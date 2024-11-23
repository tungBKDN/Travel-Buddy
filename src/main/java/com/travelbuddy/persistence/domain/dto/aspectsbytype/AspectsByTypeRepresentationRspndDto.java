package com.travelbuddy.persistence.domain.dto.aspectsbytype;

import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.*;

@Getter
@Setter
public class AspectsByTypeRepresentationRspndDto {
    private Integer aspectId;
    private SiteTypeRspnDto siteType;
    private String aspectName;
}