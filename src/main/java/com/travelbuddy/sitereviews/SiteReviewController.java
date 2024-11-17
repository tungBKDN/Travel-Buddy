package com.travelbuddy.sitereviews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelbuddy.common.constants.MediaTypeEnum;
import com.travelbuddy.common.utils.FilenameUtils;
import com.travelbuddy.common.utils.RequestUtils;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.FileEntity;
import com.travelbuddy.persistence.domain.entity.ReviewMediaEntity;
import com.travelbuddy.upload.cloud.StorageService;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/site-reviews")
public class SiteReviewController {
    private final SiteReviewService siteReviewService;
    private final StorageService storageService;
    private final RequestUtils requestUtils;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Void> postSiteReview(@RequestParam("review") String review,
                                               @RequestParam(value = "images", required = false) Optional<List<MultipartFile>> images,
                                               @RequestParam(value = "videos", required = false) Optional<List<MultipartFile>> videos) {
        SiteReviewCreateRqstDto siteReviewCreateRqstDto;
        try {
            siteReviewCreateRqstDto = objectMapper.readValue(review, SiteReviewCreateRqstDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<ReviewMediaEntity> reviewMedias = new ArrayList<>();

        images.ifPresent(imageList -> {
            for (MultipartFile image : imageList) {
                try {
                    FileUploadRqstDto fileUploadRqstDto = FileUploadRqstDto.builder()
                            .inputStream(image.getInputStream())
                            .folder("site-reviews")
                            .mimeType(image.getContentType())
                            .extension(FilenameUtils.getExtension(image.getOriginalFilename()).orElse(null))
                            .build();

                    FileRspnDto uploadedFile = storageService.uploadFile(fileUploadRqstDto);

                    reviewMedias.add(ReviewMediaEntity.builder()
                            .media(FileEntity.builder()
                                    .id(uploadedFile.getId())
                                    .url(uploadedFile.getUrl())
                                    .build())
                            .mediaType(MediaTypeEnum.IMAGE.name())
                            .build());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        videos.ifPresent(videoList -> {
            for (MultipartFile video : videoList) {
                try {
                    FileUploadRqstDto fileUploadRqstDto = FileUploadRqstDto.builder()
                            .inputStream(video.getInputStream())
                            .folder("site-reviews")
                            .mimeType(video.getContentType())
                            .extension(FilenameUtils.getExtension(video.getOriginalFilename()).orElse(null))
                            .build();

                FileRspnDto uploadedFile = storageService.uploadFile(fileUploadRqstDto);

                reviewMedias.add(ReviewMediaEntity.builder()
                        .media(FileEntity.builder()
                                .id(uploadedFile.getId())
                                .url(uploadedFile.getUrl())
                                .build())
                        .mediaType(MediaTypeEnum.VIDEO.name())
                        .build());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        siteReviewService.createSiteReview(siteReviewCreateRqstDto, reviewMedias);
        return ResponseEntity.created(URI.create("/api/site-reviews/" + siteReviewCreateRqstDto.getSiteId())).build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Object> getSiteReview(@PathVariable int reviewId) {
        SiteReviewDetailRspnDto siteReview = siteReviewService.getSiteReviewById(reviewId);

        return ResponseEntity.ok(siteReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateSiteReview(@PathVariable int reviewId,
                                                 @RequestParam("review") String review,
                                                 @RequestParam("images") Optional<List<MultipartFile>> images,
                                                 @RequestParam("videos") Optional<List<MultipartFile>> videos) {

        SiteReviewUpdateRqstDto siteReviewUpdateRqstDto;
        try {
            siteReviewUpdateRqstDto = objectMapper.readValue(review, SiteReviewUpdateRqstDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        int userId = requestUtils.getUserIdCurrentRequest();
        if (siteReviewService.getSiteReviewOwner(reviewId) != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<ReviewMediaEntity> reviewMedias = new ArrayList<>();

        images.ifPresent(imageList -> {
            for (MultipartFile image : imageList) {
                try {
                    FileUploadRqstDto fileUploadRqstDto = FileUploadRqstDto.builder()
                            .inputStream(image.getInputStream())
                            .folder("site-reviews")
                            .mimeType(image.getContentType())
                            .extension(FilenameUtils.getExtension(image.getOriginalFilename()).orElse(null))
                            .build();

                FileRspnDto uploadedFile = storageService.uploadFile(fileUploadRqstDto);

                reviewMedias.add(ReviewMediaEntity.builder()
                        .media(FileEntity.builder()
                                .id(uploadedFile.getId())
                                .url(uploadedFile.getUrl())
                                .build())
                        .mediaType(MediaTypeEnum.IMAGE.name())
                        .build());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        videos.ifPresent(videoList -> {
            for (MultipartFile video : videoList) {
                try {
                    FileUploadRqstDto fileUploadRqstDto = FileUploadRqstDto.builder()
                            .inputStream(video.getInputStream())
                            .folder("site-reviews")
                            .mimeType(video.getContentType())
                            .extension(FilenameUtils.getExtension(video.getOriginalFilename()).orElse(null))
                            .build();

                FileRspnDto uploadedFile = storageService.uploadFile(fileUploadRqstDto);

                reviewMedias.add(ReviewMediaEntity.builder()
                        .media(FileEntity.builder()
                                .id(uploadedFile.getId())
                                .url(uploadedFile.getUrl())
                                .build())
                        .mediaType(MediaTypeEnum.VIDEO.name())
                        .build());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        siteReviewService.updateSiteReview(reviewId, siteReviewUpdateRqstDto, reviewMedias);
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
}
