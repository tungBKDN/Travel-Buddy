package com.travelbuddy.site.user;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import com.travelbuddy.persistence.repository.SiteRepository;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;
    private final SiteApprovalRepository siteApprovalRepository;
    private final SiteVersionService siteVersionService;
    private final SiteRepository siteRepository;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> postSite(@RequestBody @Valid SiteCreateRqstDto siteCreateRqstDto) {
        Integer siteID = siteService.createSiteWithSiteVersion(siteCreateRqstDto);
        return ResponseEntity.created(URI.create("/api/sites/" + siteID)).build();
    }

    @GetMapping("/{siteId}")
    public ResponseEntity<Object> getValidSiteRepresention(@PathVariable int siteId) {
        /* This API returns the representation of siteID, publicly */
        Optional<Integer> latestApprovedVersionId = siteApprovalRepository.findLatestApprovedSiteVersionIdBySiteId(siteId);
        if (latestApprovedVersionId.isEmpty()) {
            // If there is no approved version, return 404
            return ResponseEntity.notFound().build();
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
    public ResponseEntity<Object> updateSite(@RequestBody @Valid SiteUpdateRqstDto siteUpdateRqstDto) {
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
        Integer newSiteVersionId = siteService.updateSite(siteUpdateRqstDto);
        Map<String, Object> response = new HashMap<>();
        response.put("createdURL", "api/sites/version=" + newSiteVersionId.toString());
        // Return the URI of the new site version, owner can be redirected to the new version
        return ResponseEntity.created(URI.create("/api/sites/version=" + newSiteVersionId)).body(response);
    }
}
