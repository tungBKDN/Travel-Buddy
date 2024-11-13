package com.travelbuddy.siteversion.user;

import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.entity.*;
import com.travelbuddy.persistence.repository.*;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.service.admin.ServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.travelbuddy.common.constants.GeographicLimitConstants.MAX_DEG_RADIUS;
import static com.travelbuddy.common.constants.GeographicLimitConstants.MIN_DEG_RADIUS;

@Service
@RequiredArgsConstructor
public class SiteVersionServiceImp implements SiteVersionService {
    private final SiteVersionRepository siteVersionRepository;
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final ServiceService serviceService;
    private final PhoneNumberRepository phoneNumberRepository;
    private final OpeningTimeRepository openingTimeRepository;
    private final SiteTypeRepository siteTypeRepository;

    @Override
    @Transactional
    public Integer createSiteVersion(SiteCreateRqstDto siteCreateRqstDto, Integer siteId) {
        SiteVersionEntity siteVersion = SiteVersionEntity.builder()
                .siteName(siteCreateRqstDto.getSiteName())
                .lat(siteCreateRqstDto.getLat())
                .lng(siteCreateRqstDto.getLng())
                .resolvedAddress(siteCreateRqstDto.getResolvedAddress())
                .website(siteCreateRqstDto.getWebsite())
                .createdAt(LocalDateTime.now())
                .typeId(siteCreateRqstDto.getTypeId())
                .siteId(siteId)
                .build();

        return siteVersionRepository.save(siteVersion).getId();
    }

    @Override
    @Transactional
    public SiteRepresentationDto getSiteVersionView(Integer siteVersionId) {
        SiteRepresentationDto resndBody = new SiteRepresentationDto();
        // 1. Get basic infor - siteVersion
        SiteVersionEntity siteVersionInfos = siteVersionRepository.findById(siteVersionId)
                .orElseThrow(() -> new NotFoundException("Site version not found"));

        // 1'. Get phone numbers
        List<String> phoneNumbers = phoneNumberRepository.findAllBySiteVersionId(siteVersionId)
                .orElseThrow(() -> new NotFoundException("Phone numbers not found"))
                .stream()
                .map(PhoneNumberEntity::getPhoneNumber)
                .toList();        // 1''. Get opening times
        List<OpeningTimeEntity> openingTimes = openingTimeRepository.findAllBySiteVersionId(siteVersionId).orElseThrow(() -> new NotFoundException("Opening times not found"));
        // 2. Get user basic information
        Integer userId = siteRepository.findById(siteVersionInfos.getSiteId())
                .orElseThrow(() -> new NotFoundException("Site not found"))
                .getOwnerId();

        UserEntity userInfo = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // 3. Get support services
        List<GroupedSiteServicesRspnDto> groupedServices = serviceService.getGroupedSiteServices(siteVersionId);
        resndBody.mapView(siteVersionInfos, userInfo, groupedServices, phoneNumbers, openingTimes);
        return resndBody;
    }

    @Override
    public List<MapRepresentationDto> getSitesInRange(double lat, double lng, double degRadius) {
        /*
            This function is to return Map representation of sites in the given range.
        */
        if (lat < -90 || lat > 90 || lng < -180 || lng > 180 || degRadius < MIN_DEG_RADIUS || degRadius > MAX_DEG_RADIUS) {
            throw new IllegalArgumentException("Invalid latitude or longitude");
        }
        List<MapRepresentationDto> sitesInRange = new ArrayList<>();
        // Get latest siteVersions, and approved, group by siteId
        List<SiteVersionEntity> siteVersions = siteVersionRepository.findApprovedSiteVersionsInRange(lat, lng, degRadius);
        for (SiteVersionEntity siteVersion : siteVersions) {
            SiteTypeEntity siteType = siteTypeRepository.findById(siteVersion.getTypeId())
                    .orElseThrow(() -> new NotFoundException("Site type not found"));
            MapRepresentationDto mapRepresentationDto = new MapRepresentationDto(siteVersion.getId(), siteType, siteVersion.getSiteName(), siteVersion.getLat(), siteVersion.getLng());
            sitesInRange.add(mapRepresentationDto);
        }
        return sitesInRange;
    }

    @Override
    @Transactional
    public Integer updateSiteVersion(SiteUpdateRqstDto siteUpdateRqstDto) {
        /* This method works like a CREATE method
           returns: ID of the new site version
        * */
        SiteVersionEntity siteVersion = SiteVersionEntity.builder()
                .siteName(siteUpdateRqstDto.getNewSiteName())
                .lat(siteUpdateRqstDto.getNewLat())
                .lng(siteUpdateRqstDto.getNewLng())
                .resolvedAddress(siteUpdateRqstDto.getNewResolvedAddress())
                .website(siteUpdateRqstDto.getNewWebSite())
                .createdAt(LocalDateTime.now())
                .typeId(siteUpdateRqstDto.getNewTypeId())
                .siteId(siteUpdateRqstDto.getSiteId())
                .build();
        return siteVersionRepository.save(siteVersion).getId();
    }
}
