package com.travelbuddy.site.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelbuddy.common.constants.MediaTypeEnum;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.utils.FilenameUtils;
import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.FileEntity;
import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.domain.entity.SiteMediaEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import com.travelbuddy.persistence.repository.SiteRepository;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.upload.cloud.StorageService;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import com.travelbuddy.user.UserService;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewRspnDto;
import com.travelbuddy.sitereviews.SiteReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;
    private final SiteApprovalRepository siteApprovalRepository;
    private final SiteVersionService siteVersionService;
    private final SiteRepository siteRepository;
    private final UserService userService;
    private final SiteReviewService siteReviewService;
    private final StorageService storageService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Object> postSite(@RequestParam("site") String site,
                                           @RequestParam(value = "images", required = false) Optional<List<MultipartFile>> images,
                                           @RequestParam(value = "videos", required = false) Optional<List<MultipartFile>> videos) {
        SiteCreateRqstDto siteCreateRqstDto;
        try {
            siteCreateRqstDto = objectMapper.readValue(site, SiteCreateRqstDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<SiteMediaEntity> siteMedias = new ArrayList<>();

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

                    siteMedias.add(SiteMediaEntity.builder()
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

                    siteMedias.add(SiteMediaEntity.builder()
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

        Integer siteID = siteService.createSiteWithSiteVersion(siteCreateRqstDto, siteMedias);
        return ResponseEntity.created(URI.create("/api/sites/" + siteID)).build();
    }

    @GetMapping("/{siteId}")
    public ResponseEntity<Object> getValidSiteRepresention(@PathVariable int siteId) {
        /* This API returns the representation of siteID, publicly */
        Optional<Integer> latestApprovedVersionId = siteApprovalRepository.findLatestApprovedSiteVersionIdBySiteId(siteId);
        if (latestApprovedVersionId.isEmpty()) {
            throw new NotFoundException("No approved version found for site with id: " + siteId);
        }

        // Success block
        Integer siteVersionId = latestApprovedVersionId.get();
        SiteRepresentationDto representationDto = siteVersionService.getSiteVersionView(siteVersionId);
        return ResponseEntity.ok(representationDto);
    }

    @GetMapping("/@")
    public ResponseEntity<Object> getSiteByLocation(@RequestParam double lat, @RequestParam double lng, @RequestParam int degRadius) {
        // Your logic to handle the request using lat and lon
        List<MapRepresentationDto> sitesInRange = siteVersionService.getSitesInRange(lat, lng, degRadius);
        return ResponseEntity.ok(sitesInRange);
    }

    @PutMapping
    public ResponseEntity<Object> updateSite(@RequestParam("site") String site,
                                             @RequestParam(value = "images", required = false) Optional<List<MultipartFile>> images,
                                             @RequestParam(value = "videos", required = false) Optional<List<MultipartFile>> videos) {

        SiteUpdateRqstDto siteUpdateRqstDto;
        try {
            siteUpdateRqstDto = objectMapper.readValue(site, SiteUpdateRqstDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<SiteMediaEntity> siteMedias = new ArrayList<>();

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

                    siteMedias.add(SiteMediaEntity.builder()
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

                    siteMedias.add(SiteMediaEntity.builder()
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

        // 1. Check if the site exists, if not return 404
        Integer siteID = siteRepository.findById(siteUpdateRqstDto.getSiteId()).map(SiteEntity::getId).orElse(null);
        if (siteID == null) {
            return ResponseEntity.notFound().build();
        }

        // 2. Chek if the cookie username/id is the ownerId
        Integer ownerId = siteRepository.findById(siteUpdateRqstDto.getSiteId()).map(SiteEntity::getOwnerId).orElse(null);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getUserIdByEmailOrUsername(username);
        if (!userId.equals(ownerId)) {
            return ResponseEntity.status(403).build();
        }

        // 3. Update the site
        Integer newSiteVersionId = siteService.updateSite(siteUpdateRqstDto, siteMedias);

        // Return the URI of the new site version, owner can be redirected to the new version
        return ResponseEntity.created(URI.create("/api/sites/version=" + newSiteVersionId)).build();
    }

    @GetMapping("/version={siteVersionId}")
    public ResponseEntity<Object> getSiteVersion(@PathVariable int siteVersionId) {
        // 1. Get the site representation
        SiteRepresentationDto siteRepresentationDto = siteVersionService.getSiteVersionView(siteVersionId);

        // 2. Check for the owner permission
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getUserIdByEmailOrUsername(username);
        Integer ownerId = siteRepresentationDto.getOwnerId();
        if (!userId.equals(ownerId)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(siteRepresentationDto);
    }
    
    @GetMapping("/{siteId}/reviews")
    public ResponseEntity<Object> getSiteReviews(@PathVariable int siteId,
                                                 @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        PageDto<SiteReviewRspnDto> siteTypesPage = siteReviewService.getAllSiteReviews(siteId, page);

        return ResponseEntity.ok(siteTypesPage);
    }

}
