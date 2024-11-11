package com.travelbuddy.siteversion.user;

import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;

public interface SiteVersionService {
    Integer createSiteVersion(SiteCreateRqstDto siteCreateRqstDto, Integer siteId);
    SiteRepresentationDto getSiteVersionView(Integer siteVersionId);
}
