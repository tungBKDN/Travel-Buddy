package com.travelbuddy.service.admin;

import com.travelbuddy.persistence.domain.dto.service.ServiceCreateRqstDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/site-services")
public class ServiceController {
    private final ServiceService serviceService;

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @PutMapping
    public ResponseEntity<Object> createSiteService(@RequestBody @Valid ServiceCreateRqstDto serviceCreateRqstDto) {
        serviceService.createSiteService(serviceCreateRqstDto);
        return ResponseEntity.ok().build();
    }
}
