package com.travelbuddy.site.user;

import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;

import java.util.List;

public interface SiteService {
    Integer createSite(Integer ownerID);
    Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto);
}