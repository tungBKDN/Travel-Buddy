package com.travelbuddy.sites.user;

import com.travelbuddy.phoneNumbers.user.PhoneNumberRepository;
import com.travelbuddy.phoneNumbers.user.PhoneNumberService;
import com.travelbuddy.siteVersions.user.SiteVersionService;
import com.travelbuddy.sites.dto.PostSiteDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImp implements SiteService {
    private final SiteRepository siteRepository;
    private final SiteVersionService siteVersionService;
    private final PhoneNumberService phoneNumberService;

    public SiteServiceImp(SiteRepository siteRepository, SiteVersionService siteVersionService, PhoneNumberService phoneNumberService) {
        this.siteRepository = siteRepository;
        this.siteVersionService = siteVersionService;
        this.phoneNumberService = phoneNumberService;
    }

    @Override
    public Integer createSite(Integer ownerID) {
        SiteEntity site = new SiteEntity();
        site.setOwnerId(ownerID);

        Integer siteID = siteRepository.save(site).getId();
        return siteID;
    }

    @Override
    @Transactional
    public Integer createSiteWithSiteVersion(PostSiteDto postSiteDto) {
        try {
            // 1. Save site entity into database
            Integer siteID = createSite(postSiteDto.getOwnerID());
            if (siteID == null || siteID < 0) {
                throw new RuntimeException("Failed to create site");
            }
            // 2. Save site version entity into database
            Integer siteVersionID = siteVersionService.createSiteVersion(postSiteDto, siteID);
            if (siteVersionID == null || siteVersionID < 0) {
                throw new RuntimeException("Failed to create site version");
            }
            // 3. Save phone numbers into database
            phoneNumberService.addPhoneNumbers(postSiteDto.getPhoneNumbers(), siteVersionID);
            return siteID;
        }
        // catch specific exception
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
