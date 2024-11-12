package com.travelbuddy.persistence.domain.dto.siteservice;

import com.travelbuddy.persistence.domain.entity.ServiceEntity;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceByTypeRspnDto {
    private SiteTypeEntity siteType;
    private List<GroupedSiteServicesRspnDto> groupedSiteServices;
}
