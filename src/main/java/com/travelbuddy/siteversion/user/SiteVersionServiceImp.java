package com.travelbuddy.siteversion.user;

import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import com.travelbuddy.persistence.repository.SiteRepository;
import com.travelbuddy.persistence.repository.SiteVersionRepository;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import com.travelbuddy.persistence.repository.UserRepository;
import com.travelbuddy.service.admin.ServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteVersionServiceImp implements SiteVersionService {
    private final SiteVersionRepository siteVersionRepository;
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final ServiceService serviceService;

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
    public SiteRepresentationDto getSiteVersionView(Integer siteVersionId) {
        SiteRepresentationDto resndBody = new SiteRepresentationDto();
        // 1. Get basic infor - siteVersion
        SiteVersionEntity siteVersionInfos = siteVersionRepository.findById(siteVersionId)
                .orElseThrow(() -> new NotFoundException("Site version not found"));

        // 2. Get user basic information
        Integer userId = siteRepository.findById(siteVersionInfos.getSiteId())
                .orElseThrow(() -> new NotFoundException("Site not found"))
                .getOwnerId();

        UserEntity userInfo = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // 3. Get support services
        List<GroupedSiteServicesRspnDto> groupedServices = serviceService.getGroupedSiteServices(siteVersionId);
        resndBody.mapView(siteVersionInfos, userInfo, groupedServices);
        return resndBody;
    }
}
