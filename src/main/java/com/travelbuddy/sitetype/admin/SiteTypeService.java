package com.travelbuddy.sitetype.admin;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.ServiceEntity;

import java.util.List;

public interface SiteTypeService {
    Integer createSiteType(SiteTypeCreateRqstDto siteTypeCreateRqstDto);

    PageDto<SiteTypeRspnDto> getAllSiteTypes(int page);

    PageDto<SiteTypeRspnDto> searchSiteTypes(String siteTypeSearch, int page);
    List<GroupedSiteServicesRspnDto> getAssociatedServiceGroups(Integer siteTypeId);
}

