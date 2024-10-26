package com.travelbuddy.sitetype.admin;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;

public interface SiteTypeService {
    Integer createSiteType(SiteTypeCreateRqstDto siteTypeCreateRqstDto);

    PageDto<SiteTypeRspnDto> getAllSiteTypes(int page);

    PageDto<SiteTypeRspnDto> searchSiteTypes(String siteTypeSearch, int page);
}
