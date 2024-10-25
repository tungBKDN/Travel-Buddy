package com.travelbuddy.site.user;

import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.repository.SiteRepository;
import com.travelbuddy.phonenumber.user.PhoneNumberService;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteServiceImp implements SiteService {
    private final SiteRepository siteRepository;
    private final SiteVersionService siteVersionService;
    private final PhoneNumberService phoneNumberService;

    @Override
    @Transactional
    public Integer createSite(Integer ownerId) {
        SiteEntity site = new SiteEntity();
        site.setOwnerId(ownerId);

        return siteRepository.save(site).getId();
    }

    @Override
    @Transactional
    public Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto) {
        // 1. Save site entity into database
        Integer siteId = createSite(siteCreateRqstDto.getOwnerId());

        // 2. Save site version entity into database
        Integer siteVersionID = siteVersionService.createSiteVersion(siteCreateRqstDto, siteId);

        // 3. Save phone numbers into database
        phoneNumberService.addPhoneNumbers(siteCreateRqstDto.getPhoneNumbers(), siteVersionID);
        return siteId;
    }
}
