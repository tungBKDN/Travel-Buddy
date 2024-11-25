package com.travelbuddy.site.user;

import com.travelbuddy.common.constants.PaginationLimitConstants;
import com.travelbuddy.common.constants.ReactionTypeEnum;
import com.travelbuddy.common.exception.errorresponse.ForbiddenException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.mapper.PageMapper;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.common.utils.RequestUtils;
import com.travelbuddy.fee.FeeService;
import com.travelbuddy.openningtime.user.OpeningTimeService;
import com.travelbuddy.persistence.domain.dto.site.*;
import com.travelbuddy.persistence.domain.entity.*;
import com.travelbuddy.persistence.repository.*;
import com.travelbuddy.phonenumber.user.PhoneNumberService;
import com.travelbuddy.service.admin.ServiceService;
import com.travelbuddy.siteapproval.admin.SiteApprovalService;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.upload.cloud.StorageExecutorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteServiceImp implements SiteService {
    private final SiteRepository siteRepository;
    private final SiteApprovalRepository siteApprovalRepository;
    private final SiteVersionRepository siteVersionRepository;

    private final SiteVersionService siteVersionService;
    private final PhoneNumberService phoneNumberService;
    private final OpeningTimeService openingTimeService;
    private final SiteApprovalService siteApprovalService;
    private final ServiceService serviceService;
    private final RequestUtils requestUtils;
    private final SiteReactionRepository siteReactionRepository;
    private final SiteMediaRepository siteMediaRepository;
    private final StorageExecutorService storageExecutorService;
    private final SiteVersionSpecifications siteVersionSpecifications;
    private final PageMapper pageMapper;
    private final FeeService feeService;

    @Override
    @Transactional
    public Integer createSiteWithSiteVersion(SiteCreateRqstDto siteCreateRqstDto, List<SiteMediaEntity> siteMediaEntities) {
        // 1. Save site entity into database
        int userId = requestUtils.getUserIdCurrentRequest();
        SiteEntity siteEntity = siteRepository.save(SiteEntity.builder().ownerId(userId).build());
        Integer siteId = siteEntity.getId();

        // 2. Save site version entity into database
        int siteVersionID = siteVersionService.createSiteVersion(siteCreateRqstDto, siteId);

        // 3. Save phone numbers into database
        phoneNumberService.addPhoneNumbers(siteCreateRqstDto.getPhoneNumbers(), siteVersionID);

        // 4. Save opening hours into database
        openingTimeService.addOpeningTimes(siteCreateRqstDto.getOpeningTimes(), siteVersionID);

        // 5. Add default site approval record
        siteApprovalService.createDefaultSiteApproval(siteVersionID);

        // 6. Save services into database
        if (siteCreateRqstDto.getServices() != null)
            serviceService.createServicesBySiteVersion(siteVersionID, siteCreateRqstDto.getServices());

        // 7. Save site media
        for (SiteMediaEntity siteMediaEntity : siteMediaEntities) {
            siteMediaEntity.setSite(siteEntity);
        }
        siteMediaRepository.saveAll(siteMediaEntities);

        // 8. Save fees
        feeService.addFee(siteCreateRqstDto.getFees(), siteVersionID);
        return siteId;
    }

    @Override
    @Transactional
    public Integer updateSite(SiteUpdateRqstDto siteUpdateRqstDto, List<SiteMediaEntity> siteMediaEntities) {
        SiteEntity siteEntity = siteRepository.findById(siteUpdateRqstDto.getSiteId())
                .orElseThrow(() -> new NotFoundException("Site not found"));

        int userId = requestUtils.getUserIdCurrentRequest();
        if (siteEntity.getOwnerId() != userId) {
            throw new ForbiddenException("You are not allowed to update this site");
        }

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

        // 6. Update site media
        List<String> mediaIdsToDelete = new ArrayList<>();

        siteEntity.getSiteMedias().removeIf(siteMediaEntity -> {
            if (!siteUpdateRqstDto.getMediaIds().contains(siteMediaEntity.getId())) {
                mediaIdsToDelete.add(siteMediaEntity.getMedia().getId());
                return true;
            }
            return false;
        });

        storageExecutorService.deleteFiles(mediaIdsToDelete);

        siteMediaEntities.forEach(siteMediaEntity -> siteMediaEntity.setSite(siteEntity));

        siteEntity.getSiteMedias().addAll(siteMediaEntities);

        siteRepository.save(siteEntity);

        // 7. Save fees
        feeService.addFee(siteUpdateRqstDto.getFees(), siteVersionId);

        return siteVersionId;
    }

    @Override
    public SiteBasicInfoRspnDto getSiteBasicRepresentation(Integer siteId) {
        // 1. Get the latest approved version
        Optional<Integer> latestApprovedVersionId = siteApprovalRepository.findLatestApprovedSiteVersionIdBySiteId(siteId);
        if (latestApprovedVersionId.isEmpty()) {
            throw new NotFoundException("No approved version found for site with id: " + siteId);
        }

        // 2. Get the site representation
        return siteVersionService.getSiteVersionBasicView(latestApprovedVersionId.get());
    }

    @Override
    public void likeSite(int siteId) {
        int userId = requestUtils.getUserIdCurrentRequest();

        Optional<SiteReactionEntity> siteReactionEntityOpt = siteReactionRepository.findByUserIdAndSiteId(userId, siteId);

        SiteReactionEntity siteReactionEntity;
        if (siteReactionEntityOpt.isPresent()) {
            siteReactionEntity = siteReactionEntityOpt.get();
            if (ReactionTypeEnum.LIKE.name().equals(siteReactionEntity.getReactionType())) {
                siteReactionEntity.setReactionType(null);
            } else {
                siteReactionEntity.setReactionType(ReactionTypeEnum.LIKE.name());
            }
        } else {
            siteReactionEntity = SiteReactionEntity.builder()
                    .userId(userId)
                    .siteId(siteId)
                    .reactionType(ReactionTypeEnum.LIKE.name())
                    .build();
        }
        siteReactionRepository.save(siteReactionEntity);
    }

    @Override
    public void dislikeSite(int siteId) {
        int userId = requestUtils.getUserIdCurrentRequest();

        Optional<SiteReactionEntity> siteReactionEntityOpt = siteReactionRepository.findByUserIdAndSiteId(userId, siteId);

        SiteReactionEntity siteReactionEntity;
        if (siteReactionEntityOpt.isPresent()) {
            siteReactionEntity = siteReactionEntityOpt.get();
            if (ReactionTypeEnum.DISLIKE.name().equals(siteReactionEntity.getReactionType())) {
                siteReactionEntity.setReactionType(null);
            } else {
                siteReactionEntity.setReactionType(ReactionTypeEnum.DISLIKE.name());
            }
        } else {
            siteReactionEntity = SiteReactionEntity.builder()
                    .userId(userId)
                    .siteId(siteId)
                    .reactionType(ReactionTypeEnum.DISLIKE.name())
                    .build();
        }
        siteReactionRepository.save(siteReactionEntity);
    }

    @Override
    public PageDto<SiteBasicInfoRspnDto> searchSites(String siteSearch, int page) {
        Pageable pageable = PageRequest.of(page - 1, PaginationLimitConstants.SITE_SEARCH_LIMIT);
        Specification<SiteVersionEntity> spec = siteVersionSpecifications.customSearchAndLatestApproved(siteSearch);

        Page<SiteVersionEntity> siteVersionEntities = siteVersionRepository.findAll(spec, pageable);

        return pageMapper.toPageDto(siteVersionEntities.map(SiteBasicInfoRspnDto::new));
    }

    @Override
    public PageDto<SiteBasicInfoRspnDto> discoverSites(int page) {
        Pageable pageable = PageRequest.of(page - 1, PaginationLimitConstants.SITE_DISCOVER_LIMIT);
        Specification<SiteVersionEntity> spec = siteVersionSpecifications.customSearchAndLatestApproved(null);

        Page<SiteVersionEntity> siteVersionEntities = siteVersionRepository.findAll(spec, pageable);

        return pageMapper.toPageDto(siteVersionEntities.map(SiteBasicInfoRspnDto::new));
    }
}
