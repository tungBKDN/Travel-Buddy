package com.travelbuddy.siteversion.user;

import com.travelbuddy.persistence.domain.dto.site.*;

import java.util.List;

public interface SiteVersionService {
    int createSiteVersion(SiteCreateRqstDto siteCreateRqstDto, int siteId);
    SiteRepresentationDto getSiteVersionView(Integer siteVersionId);
    List<MapRepresentationDto> getSitesInRange(double lat, double lng, double degRadius);
    Integer updateSiteVersion(SiteUpdateRqstDto siteUpdateRqstDto);

    SiteBasicInfoRspnDto getSiteVersionBasicView(Integer siteVersionId);
}
