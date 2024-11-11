package com.travelbuddy.site.user;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import com.travelbuddy.siteversion.user.SiteVersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;
    private final SiteApprovalRepository siteApprovalRepository;
    private final SiteVersionService siteVersionService;

    @PostMapping
    public ResponseEntity<Object> postSite(@RequestBody @Valid SiteCreateRqstDto siteCreateRqstDto) {
        Integer siteID = siteService.createSiteWithSiteVersion(siteCreateRqstDto);
        return ResponseEntity.created(URI.create("/api/sites/" + siteID)).build();
    }

    @GetMapping("/{siteId}")
    public ResponseEntity<Object> getValidSiteRepresention(@PathVariable int siteId) {
        /* This API returns the representation of siteID, publicly */
        Optional<SiteApprovalEntity> latestApprovedVersionId = siteApprovalRepository.findTopBySiteVersionIdAndStatusOrderByApprovedAtDesc(siteId, ApprovalStatusEnum.APPROVED);
        if (latestApprovedVersionId.isEmpty()) {
            // If there is no approved version, return 404
            return ResponseEntity.notFound().build();
        }

        // Success block
        Integer siteVersionId = latestApprovedVersionId.get().getSiteVersionId();
        SiteRepresentationDto representationDto = siteVersionService.getSiteVersionView(siteVersionId);
        return ResponseEntity.ok(representationDto);
    }

    @GetMapping("/@")
    public ResponseEntity<Object> getSiteByLocation(@RequestParam double lat, @RequestParam double lng, @RequestParam int degRadius) {
        // Your logic to handle the request using lat and lon
        List<MapRepresentationDto> sitesInRange = siteVersionService.getSitesInRange(lat, lng, degRadius);
        return ResponseEntity.ok(sitesInRange);
    }
}
