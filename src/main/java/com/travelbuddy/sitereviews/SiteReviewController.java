package com.travelbuddy.sitereviews;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.sitereview.MySiteReviewRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewUpdateRqstDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/site-reviews")
public class SiteReviewController {
    private final SiteReviewService siteReviewService;

    @PostMapping
    public ResponseEntity<Void> postSiteReview(@RequestBody @Valid SiteReviewCreateRqstDto siteReviewCreateRqstDto) {
        siteReviewService.createSiteReview(siteReviewCreateRqstDto);
        return ResponseEntity.created(URI.create("/api/site-reviews/" + siteReviewCreateRqstDto.getSiteId())).build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Object> getSiteReview(@PathVariable int reviewId) {
        SiteReviewDetailRspnDto siteReview = siteReviewService.getSiteReviewById(reviewId);

        return ResponseEntity.ok(siteReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateSiteReview(@PathVariable int reviewId,
                                                @RequestBody @Valid  SiteReviewUpdateRqstDto siteReviewUpdateRqstDto) {


        siteReviewService.updateSiteReview(reviewId, siteReviewUpdateRqstDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteSiteReview(@PathVariable int reviewId) {
        siteReviewService.deleteSiteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<Void> likeSiteReview(@PathVariable int reviewId) {
        siteReviewService.likeSiteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reviewId}/dislike")
    public ResponseEntity<Void> dislikeSiteReview(@PathVariable int reviewId) {
        siteReviewService.dislikeSiteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-reviews")
    public ResponseEntity<Object> getMySiteReviews(@RequestParam(name = "q", required = false, defaultValue = "") String reviewSearch,
                                                   @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        PageDto<MySiteReviewRspnDto> mySiteReviews = siteReviewService.getMySiteReviews(reviewSearch, page);

        return ResponseEntity.ok(mySiteReviews);
    }
}
