package com.travelbuddy.siteVersions.user;

import com.travelbuddy.sites.dto.PostSiteDto;

public interface SiteVersionService {
    Integer createSiteVersion(PostSiteDto postSiteDto, Integer siteID);
}
