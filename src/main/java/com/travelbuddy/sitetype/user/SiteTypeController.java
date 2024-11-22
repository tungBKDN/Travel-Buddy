package com.travelbuddy.sitetype.user;

import com.travelbuddy.common.exception.errorresponse.NotFoundException;
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
    private final ServiceService serviceService;
    private final SiteTypeRepository siteTypeRepository;

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

    }
}