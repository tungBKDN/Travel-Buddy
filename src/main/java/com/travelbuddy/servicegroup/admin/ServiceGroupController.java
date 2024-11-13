package com.travelbuddy.servicegroup.admin;

import com.travelbuddy.persistence.domain.dto.servicegroup.ServiceGroupCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceGroupEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.travelbuddy.common.paging.PageDto;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/service-groups")
public class ServiceGroupController {
    private final ServiceGroupService serviceGroupService;

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @PostMapping
    public ResponseEntity<Object> createServiceGroup(@RequestBody @Valid ServiceGroupCreateRqstDto serviceCreateRqstDto) {
        Integer id = serviceGroupService.createServiceGroup(serviceCreateRqstDto);
        return ResponseEntity.ok(URI.create("/api/admin/service-groups/" + id));
    }

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getServiceGroup(@PathVariable Integer id) {
        ServiceGroupEntity serviceGroupEntity = serviceGroupService.getServiceGroup(id);
        return ResponseEntity.ok(serviceGroupEntity);
    }

    @PreAuthorize("hasAuthority('MANAGE_SITE_TYPES')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateServiceGroup(@PathVariable Integer id, @RequestBody @Valid ServiceGroupCreateRqstDto serviceCreateRqstDto) {
        serviceGroupService.updateServiceGroup(id, serviceCreateRqstDto);
        return ResponseEntity.ok().build();
    }
}
