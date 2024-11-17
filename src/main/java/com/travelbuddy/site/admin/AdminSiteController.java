package com.travelbuddy.site.admin;

import com.travelbuddy.persistence.domain.dto.site.MapRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteRepresentationDto;
import com.travelbuddy.persistence.domain.dto.site.SiteUpdateRqstDto;
import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import com.travelbuddy.persistence.repository.SiteRepository;
import com.travelbuddy.site.user.SiteService;
import com.travelbuddy.siteversion.user.SiteVersionService;
import com.travelbuddy.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sites")
@RequiredArgsConstructor
public class AdminSiteController {
    private final SiteVersionService siteVersionService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('MANAGE_SITES')")
    @GetMapping("/version={siteVersionId}")
    public ResponseEntity<Object> getSiteVersion(@PathVariable int siteVersionId) {
        // 1. Get the site representation
        SiteRepresentationDto siteRepresentationDto = siteVersionService.getSiteVersionView(siteVersionId);

        return ResponseEntity.ok(siteRepresentationDto);
    }
}
