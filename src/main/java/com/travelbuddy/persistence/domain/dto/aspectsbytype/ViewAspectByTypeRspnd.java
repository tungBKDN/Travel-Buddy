package com.travelbuddy.persistence.domain.dto.aspectsbytype;

import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ViewAspectByTypeRspnd {
    private Integer aspectId;
    private SiteTypeEntity siteType;
    private String aspectName;
}
