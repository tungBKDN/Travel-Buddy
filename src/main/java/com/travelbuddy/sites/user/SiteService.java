package com.travelbuddy.sites.user;

import com.travelbuddy.sites.dto.PostSiteDto;
import org.springframework.stereotype.Service;

public interface SiteService {
    Integer createSite(Integer ownerID);
    Integer createSiteWithSiteVersion(PostSiteDto postSiteDto);
}