package com.travelbuddy.siteversion.user;

import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import com.travelbuddy.persistence.repository.SiteVersionRepository;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
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
}
