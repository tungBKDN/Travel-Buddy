package com.travelbuddy.site.user;

import com.travelbuddy.persistence.domain.dto.site.*;
import com.travelbuddy.persistence.domain.entity.SiteMediaEntity;

import java.util.List;

public interface SiteService {
    Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto, List<SiteMediaEntity> siteMediaEntities);
    Integer updateSite(SiteUpdateRqstDto siteUpdateRqstDto, List<SiteMediaEntity> siteMediaEntities);
    SiteBasicInfoRspnDto getSiteBasicRepresentation(Integer siteID);

    void likeSite(int siteId);

    void dislikeSite(int siteId);
}