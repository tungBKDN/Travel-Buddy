package com.travelbuddy.sitereviews;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.sitereview.*;

import java.util.List;

public interface SiteReviewService {
    void createSiteReview(SiteReviewCreateRqstDto siteReviewCreateRqstDt);

    PageDto<SiteReviewRspnDto> getAllSiteReviews(int siteId, int page);

    void updateSiteReview(int siteReviewId, SiteReviewUpdateRqstDto siteReviewUpdateRqstDto);

    void deleteSiteReview(int reviewId);

    SiteReviewDetailRspnDto getSiteReviewById(int reviewId);

    void likeSiteReview(int reviewId);

    void dislikeSiteReview(int reviewId);

    PageDto<MySiteReviewRspnDto> getMySiteReviews(String reviewSearch, int page);
}
