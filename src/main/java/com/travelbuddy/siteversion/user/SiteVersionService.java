package com.travelbuddy.siteversion.user;

import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;

import java.util.List;

public interface SiteVersionService {
    Integer createSiteVersion(SiteCreateRqstDto siteCreateRqstDto, Integer siteId);
    SiteRepresentationDto getSiteVersionView(Integer siteVersionId);
    List<MapRepresentationDto> getSitesInRange(double lat, double lng, double degRadius);
    Integer updateSiteVersion(SiteUpdateRqstDto siteUpdateRqstDto);
}
