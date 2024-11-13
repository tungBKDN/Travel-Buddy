package com.travelbuddy.site.user;

import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.openningtime.user.OpeningTimeService;
import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import com.travelbuddy.persistence.repository.*;
import com.travelbuddy.phonenumber.user.PhoneNumberService;
import com.travelbuddy.service.admin.ServiceService;
import com.travelbuddy.siteapproval.admin.SiteApprovalService;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteServiceImp implements SiteService {
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final SiteApprovalRepository siteApprovalRepository;
    private final SiteVersionRepository siteVersionRepository;

    private final SiteVersionService siteVersionService;
    private final PhoneNumberService phoneNumberService;
    private final OpeningTimeService openingTimeService;
    private final SiteApprovalService siteApprovalService;
    private final ServiceService serviceService;

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

        // 4. Save opening hours into database
        openingTimeService.addOpeningTimes(siteCreateRqstDto.getOpeningTimes(), siteVersionID);

        // 5. Add default site approval record
        siteApprovalService.createDefaultSiteApproval(siteVersionID);

        // 6. Save services into database
        if (siteCreateRqstDto.getServices() != null)
            serviceService.createServicesBySiteVersion(siteVersionID, siteCreateRqstDto.getServices());
        return siteId;
    }

    @Override
    @Transactional
    public Integer updateSite(SiteUpdateRqstDto siteUpdateRqstDto) {
        // 1. Save version entity into database
        Integer siteVersionId = siteVersionService.updateSiteVersion(siteUpdateRqstDto);

        // 2. Save phone numbers into database
        phoneNumberService.addPhoneNumbers(siteUpdateRqstDto.getNewPhoneNumbers(), siteVersionId);

        // 3. Save opening hours into database
        openingTimeService.addOpeningTimes(siteUpdateRqstDto.getNewOpeningTimes(), siteVersionId);

        // 4. Add default site approval record
        siteApprovalService.createDefaultSiteApproval(siteVersionId);

        // 5. Save services into database
        if (siteUpdateRqstDto.getNewServices() != null)
            serviceService.createServicesBySiteVersion(siteVersionId, siteUpdateRqstDto.getNewServices());

        return siteVersionId;
    }
}
