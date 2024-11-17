package com.travelbuddy.sitereviews;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.ReviewMediaEntity;

import java.util.List;

public interface SiteReviewService {
    void createSiteReview(SiteReviewCreateRqstDto siteReviewCreateRqstDt, List<ReviewMediaEntity> reviewMediaEntities);

    PageDto<SiteReviewRspnDto> getAllSiteReviews(int siteId, int page);

    void updateSiteReview(int siteReviewId, SiteReviewUpdateRqstDto siteReviewUpdateRqstDto, List<ReviewMediaEntity> reviewMediaEntities);

    void deleteSiteReview(int reviewId);

    SiteReviewDetailRspnDto getSiteReviewById(int reviewId);

    Integer getSiteReviewOwner(int reviewId);

}
