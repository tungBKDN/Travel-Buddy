package com.travelbuddy.siteTypes.admin;

import com.travelbuddy.siteTypes.dto.PostSiteTypeDto;
import com.travelbuddy.siteTypes.dto.ResponseAllSiteTypes;
import com.travelbuddy.siteTypes.exception.SiteTypeExistedException;
import com.travelbuddy.siteTypes.dto.ExistedResponse;
import com.travelbuddy.siteTypes.user.SiteTypeEntity;
import jakarta.validation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SiteTypeController {
    private final SiteTypeService siteTypeService;
    private final SiteTypeRepository siteTypeRepository;

    public SiteTypeController(SiteTypeService siteTypeService, SiteTypeRepository siteTypeRepository) {
        this.siteTypeService = siteTypeService;
        this.siteTypeRepository = siteTypeRepository;
    }

    @PostMapping("admin/api/siteTypes")
    public ResponseEntity<Object> postSiteType(@RequestBody @Valid PostSiteTypeDto postSiteTypeDto) {
        try {
            Integer siteTypeID = siteTypeService.createSiteType(postSiteTypeDto);
            if (siteTypeID == -1) {
                return ResponseEntity.badRequest().body("An error during posting new information");
            }
            return ResponseEntity.created(URI.create(String.format("/admin/api/siteTypes/%d", siteTypeID))).build();
        } catch (SiteTypeExistedException e) {
            ExistedResponse existedResponse = new ExistedResponse();
            existedResponse.setExistedSiteId(e.getExitedSiteTypeId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existedResponse);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error during posting new information");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/siteTypes")
    public ResponseEntity<Object> getSiteTypes(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer pageNumber
    ) {
        // Check for pagination
        if (limit != null && pageNumber != null) {
            return getAllSiteTypesPaging(pageNumber, limit);
        }
        return getAllSiteTypes();
    }

    public ResponseEntity<Object> getAllSiteTypesPaging(Integer paging, Integer limit) {
        Optional<List<SiteTypeEntity>> allSiteTypes = siteTypeRepository.getAllSiteTypes(PageRequest.of(paging, limit));
        if (allSiteTypes.isEmpty()) {
            return ResponseEntity.ok(new ResponseAllSiteTypes());
        }
        // Flattened Optional
        List<SiteTypeEntity> flattened = allSiteTypes.get();
        ResponseAllSiteTypes response = new ResponseAllSiteTypes(flattened);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> getAllSiteTypes() {
        Optional<List<SiteTypeEntity>> allSiteTypes = siteTypeRepository.getAllSiteTypes();
        if (allSiteTypes.isEmpty()) {
            return ResponseEntity.ok(new ResponseAllSiteTypes());
        }
        // Flattened Optional
        List<SiteTypeEntity> flattened = allSiteTypes.get();
        ResponseAllSiteTypes response = new ResponseAllSiteTypes(flattened);
        return ResponseEntity.ok(response);
    }
}
