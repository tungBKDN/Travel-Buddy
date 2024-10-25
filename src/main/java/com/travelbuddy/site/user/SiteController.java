package com.travelbuddy.site.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travelbuddy.persistence.domain.dto.site.SiteCreateRqstDto;

import java.net.URI;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping
    public ResponseEntity<Object> postSite(@RequestBody @Valid SiteCreateRqstDto siteCreateRqstDto) {
        Integer siteID = siteService.createSiteWithSiteVersion(siteCreateRqstDto);
        return ResponseEntity.created(URI.create("/api/sites/" + siteID)).build();
    }
}
