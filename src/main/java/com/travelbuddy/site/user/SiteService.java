package com.travelbuddy.site.user;

import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;

import java.util.List;

public interface SiteService {
    Integer createSite(Integer ownerID);
    Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto);
    Integer updateSite(SiteUpdateRqstDto siteUpdateRqstDto);
}