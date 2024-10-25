package com.travelbuddy.siteversion.user;

import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;

public interface SiteVersionService {
    Integer createSiteVersion(SiteCreateRqstDto siteCreateRqstDto, Integer siteId);
}
