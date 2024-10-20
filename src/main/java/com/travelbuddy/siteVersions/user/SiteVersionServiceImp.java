package com.travelbuddy.siteVersions.user;

import com.travelbuddy.sites.dto.PostSiteDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SiteVersionServiceImp implements SiteVersionService {
    private final SiteVersionRepository siteVersionRepository;

    public SiteVersionServiceImp(SiteVersionRepository siteVersionRepository) {
        this.siteVersionRepository = siteVersionRepository;
    }

    @Override
    @Transactional
    public Integer createSiteVersion(PostSiteDto postSiteDto, Integer siteID) {
        try {
            SiteVersionEntity siteVersion = new SiteVersionEntity();
            // Assign values to siteVersion from postSiteDto
            siteVersion.setSiteName(postSiteDto.getSiteName());
            siteVersion.setLat(postSiteDto.getLat());
            siteVersion.setLon(postSiteDto.getLon());
            siteVersion.setResolvedAddress(postSiteDto.getResolvedAddress());
            siteVersion.setWebsite(postSiteDto.getWebsite());
            // Set createdAt to current time
            siteVersion.setCreatedAt(LocalDateTime.now());
            siteVersion.setTypeID(postSiteDto.getTypeID());
            siteVersion.setSiteID(siteID);
            siteVersion.setParentVersionID(null); // Default value for parentVersionID

            Integer siteVersionID = siteVersionRepository.save(siteVersion).getID();
            return siteVersionID;
        } catch (Exception e) {
            return -1; // Default value for error
        }

    }
}
