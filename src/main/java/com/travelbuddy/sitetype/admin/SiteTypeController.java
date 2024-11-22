package com.travelbuddy.sitetype.admin;

import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.repository.SiteTypeRepository;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/site-types")
public class SiteTypeController {
    private final SiteTypeService siteTypeService;
    private final SiteTypeRepository siteTypeRepository;

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @PostMapping
    public ResponseEntity<Object> postSiteType(@RequestBody @Valid SiteTypeCreateRqstDto siteTypeCreateRqstDto) {
        if (siteTypeRepository.existsByTypeNameIgnoreCase(siteTypeCreateRqstDto.getSiteTypeName()))
            throw new DataAlreadyExistsException("Site type already exists");

        Integer siteTypeId =  siteTypeService.createSiteType(siteTypeCreateRqstDto);
        return ResponseEntity.created(URI.create("/admin/api/siteTypes/" + siteTypeId)).build();
    }

    @GetMapping
    public ResponseEntity<Object> getSiteTypes(@RequestParam(name = "q", required = false, defaultValue = "") String siteTypeSearch,
                                               @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        PageDto<SiteTypeRspnDto> siteTypesPage = siteTypeSearch.trim().isEmpty()
                ? siteTypeService.getAllSiteTypes(page)
                : siteTypeService.searchSiteTypes(siteTypeSearch, page);

        return ResponseEntity.ok(siteTypesPage);
    }

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @PutMapping("/{siteTypeId}")
    public ResponseEntity<Object> updateSiteType(@PathVariable int siteTypeId, @RequestBody @Valid SiteTypeCreateRqstDto siteTypeCreateRqstDto) {
        siteTypeService.updateSiteType(siteTypeId, siteTypeCreateRqstDto);
        return ResponseEntity.noContent().build();
    }

}
