package com.travelbuddy.sitetype.user;

import com.travelbuddy.aspectsbytype.admin.AspectsByTypeService;
import com.travelbuddy.common.constants.PaginationLimitConstants;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.aspectsbytype.AspectsByTypeRepresentationRspndDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.dto.siteservice.ServiceByTypeRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.repository.SiteTypeRepository;
import com.travelbuddy.service.admin.ServiceService;
import com.travelbuddy.sitetype.admin.SiteTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("SiteTypeControllerUser")
@RequiredArgsConstructor
@RequestMapping("/api/site-types")
public class SiteTypeController {
    private final SiteTypeService siteTypeService;
    private final SiteTypeRepository siteTypeRepository;
    private final AspectsByTypeService aspectsByTypeService;

    @GetMapping("/{siteTypeId}/services")
    public ResponseEntity<Object> getAssociatedServices(@PathVariable Integer siteTypeId) {
        // 1. Get siteType from siteTypeId, check if Not found
        SiteTypeEntity siteType = siteTypeRepository.findById(siteTypeId)
                .orElseThrow(() -> new NotFoundException("Site type not found"));
        List<GroupedSiteServicesRspnDto> groupedSiteServices = siteTypeService.getAssociatedServiceGroups(siteTypeId);
        ServiceByTypeRspnDto servicesByTypeRspnDto = new ServiceByTypeRspnDto();
        SiteTypeRspnDto siteTypeRspnDto = new SiteTypeRspnDto(siteType);
        servicesByTypeRspnDto.setSiteType(siteTypeRspnDto);
        servicesByTypeRspnDto.setGroupedSiteServices(groupedSiteServices);
        return ResponseEntity.ok(servicesByTypeRspnDto);
    }

    @GetMapping("/{siteTypeId}/aspects")
    public ResponseEntity<Object> getAssociatedAspects(@PathVariable Integer siteTypeId) {
        List< AspectsByTypeRepresentationRspndDto> aspects = aspectsByTypeService.getAspectsByTypeId(siteTypeId);
        return ResponseEntity.ok(aspects);
    }

    @GetMapping
    public ResponseEntity<Object> getSiteTypes(@RequestParam(name = "q", required = false, defaultValue = "") String siteTypeSearch,
                                               @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "limit", required = false) Integer limit) {

        // Check for limit is set
        if (limit == null) {
            limit = PaginationLimitConstants.SITE_TYPE_LIMIT;
        }
        PageDto<SiteTypeRspnDto> siteTypesPage = siteTypeSearch.trim().isEmpty()
                ? siteTypeService.getAllSiteTypes(page, limit)
                : siteTypeService.searchSiteTypes(siteTypeSearch, page, limit);

        return ResponseEntity.ok(siteTypesPage);
    }

}