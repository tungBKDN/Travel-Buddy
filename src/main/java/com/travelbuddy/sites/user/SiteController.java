package com.travelbuddy.sites.user;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.travelbuddy.sites.dto.PostSiteDto;

import java.net.URI;

@RestController
public class SiteController {
    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/api/sites")
    public ResponseEntity<Object> postSite(@RequestBody @Valid PostSiteDto postSiteDto) {
        try {
            Integer siteID = siteService.createSiteWithSiteVersion(postSiteDto);
            return ResponseEntity.created(URI.create(String.format("/api/sites/%d", siteID))).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("An error during posting new information");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
