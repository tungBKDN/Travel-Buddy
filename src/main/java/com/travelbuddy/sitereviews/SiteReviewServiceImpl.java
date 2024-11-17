package com.travelbuddy.sitereviews;

import com.travelbuddy.common.constants.ReactionTypeEnum;
import com.travelbuddy.common.exception.errorresponse.ForbiddenException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.mapper.PageMapper;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.common.utils.RequestUtils;
import com.travelbuddy.mapper.SiteReviewMapper;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.ReviewMediaEntity;
import com.travelbuddy.persistence.domain.entity.ReviewReactionEntity;
import com.travelbuddy.persistence.domain.entity.SiteReviewEntity;
import com.travelbuddy.persistence.repository.ReviewMediaRepository;
import com.travelbuddy.persistence.repository.ReviewReactionRepository;
import com.travelbuddy.persistence.repository.SiteReviewRepository;
import com.travelbuddy.upload.cloud.StorageExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.travelbuddy.common.constants.PaginationLimitConstants.SITE_REVIEW_LIMIT;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteReviewServiceImpl implements SiteReviewService {
    private final SiteReviewRepository siteReviewRepository;
    private final PageMapper pageMapper;
    private final SiteReviewMapper siteReviewMapper;
    private final SiteReviewSpecifications siteReviewSpecifications;
    private final ReviewMediaRepository reviewMediaRepository;
    private final StorageExecutorService storageExecutorService;
    private final ReviewReactionRepository reviewReactionRepository;
    private final RequestUtils requestUtils;

    @Override
    public void createSiteReview(SiteReviewCreateRqstDto siteReviewCreateRqstDto, List<ReviewMediaEntity> reviewMediaEntities) {
        int userId = requestUtils.getUserIdCurrentRequest();

        SiteReviewEntity siteReviewEntity = SiteReviewEntity.builder()
                .siteId(siteReviewCreateRqstDto.getSiteId())
                .userId(userId)
                .comment(siteReviewCreateRqstDto.getComment())
                .generalRating(siteReviewCreateRqstDto.getGeneralRating())
                .arrivalDate(siteReviewCreateRqstDto.getArrivalDate())
                .build();

        siteReviewEntity = siteReviewRepository.saveAndFlush(siteReviewEntity);

        for (ReviewMediaEntity reviewMediaEntity : reviewMediaEntities) {
            reviewMediaEntity.setReview(siteReviewEntity);
        }

        reviewMediaRepository.saveAll(reviewMediaEntities);
    }

    @Override
    public PageDto<SiteReviewRspnDto> getAllSiteReviews(int siteId, int page) {
        Specification<SiteReviewEntity> specification = siteReviewSpecifications.customeScoreBySiteId((long) siteId);

        Pageable pageable = PageRequest.of(page - 1, SITE_REVIEW_LIMIT);
        Page<SiteReviewEntity> siteReviews = siteReviewRepository.findAll(specification, pageable);

        int userId = requestUtils.getUserIdCurrentRequest();

        return pageMapper.toPageDto(siteReviews.map(siteReviewEntity -> siteReviewMapper.siteReviewEntityToSiteReviewRspnDto(siteReviewEntity, userId)));
    }

    @Override
    public void updateSiteReview(int siteReviewId, SiteReviewUpdateRqstDto siteReviewUpdateRqstDto, List<ReviewMediaEntity> reviewMediaEntities) {
        SiteReviewEntity siteReviewEntity = siteReviewRepository.findById((long) siteReviewId)
                .orElseThrow(() -> new NotFoundException("Site review not found"));

        int userId = requestUtils.getUserIdCurrentRequest();
        if (siteReviewEntity.getUserId() != userId) {
            throw new ForbiddenException("You are not allowed to update this review");
        }

        List<String> mediaIdsToDelete = new ArrayList<>();

        siteReviewEntity.getReviewMedias().removeIf(reviewMediaEntity -> {
            if (!siteReviewUpdateRqstDto.getMediaIds().contains(reviewMediaEntity.getId())) {
                mediaIdsToDelete.add(reviewMediaEntity.getMedia().getId());
                return true;
            }
            return false;
        });

        storageExecutorService.deleteFiles(mediaIdsToDelete);

        reviewMediaEntities.forEach(reviewMediaEntity -> reviewMediaEntity.setReview(siteReviewEntity));

        siteReviewEntity.setComment(siteReviewUpdateRqstDto.getComment());
        siteReviewEntity.setGeneralRating(siteReviewUpdateRqstDto.getGeneralRating());
        siteReviewEntity.setArrivalDate(siteReviewUpdateRqstDto.getArrivalDate());
        // To update existing reviewMedias, we need to clear the old list and add the new list
        siteReviewEntity.getReviewMedias().addAll(reviewMediaEntities);

        siteReviewRepository.save(siteReviewEntity);
    }

    @Override
    public void deleteSiteReview(int reviewId) {
        SiteReviewEntity siteReviewEntity = siteReviewRepository.findById((long) reviewId)
                .orElseThrow(() -> new NotFoundException("Site review not found"));

        int userId = requestUtils.getUserIdCurrentRequest();
        if (siteReviewEntity.getUserId() != userId) {
            throw new ForbiddenException("You are not allowed to delete this review");
        }

        List<String> mediaIds = new ArrayList<>();
        siteReviewEntity.getReviewMedias().forEach(reviewMediaEntity -> mediaIds.add(reviewMediaEntity.getMedia().getId()));

        mediaIds.forEach(storageExecutorService::deleteFile);

//        reviewMediaRepository.deleteByReviewId(siteReviewEntity.getId());
        siteReviewRepository.delete(siteReviewEntity);
    }

    @Override
    public SiteReviewDetailRspnDto getSiteReviewById(int reviewId) {
        SiteReviewEntity siteReviewEntity = siteReviewRepository.findById((long) reviewId)
                .orElseThrow(() -> new NotFoundException("Site review not found"));

        return siteReviewMapper.siteReviewEntityToSiteReviewDetailRspnDto(siteReviewEntity);
    }

    @Override
    public Integer getSiteReviewOwner(int reviewId) {
        SiteReviewEntity siteReviewEntity = siteReviewRepository.findById((long) reviewId)
                .orElseThrow(() -> new NotFoundException("Site review not found"));

        return siteReviewEntity.getUserId();
    }

}
