package com.travelbuddy.site.user;

import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;

public interface SiteService {
    Integer createSite(Integer ownerID);
    Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto);
}