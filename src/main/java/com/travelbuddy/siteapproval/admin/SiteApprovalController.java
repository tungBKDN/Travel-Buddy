package com.travelbuddy.siteapproval.admin;

import com.travelbuddy.auth.service.AdminService;
import com.travelbuddy.persistence.domain.dto.siteapproval.UpdateSiteApprovalRqstDto;
import com.travelbuddy.user.UserService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/site-approval")
public class SiteApprovalController {
    private final SiteApprovalService siteApprovalService;
    private final AdminService adminService;

    @PreAuthorize("hasAuthority('MANAGE_SITES')")
    @PutMapping
    public ResponseEntity<Object> approveSite(@RequestBody @Valid UpdateSiteApprovalRqstDto siteApprovalRqstDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = adminService.getAdminIdByEmailOrUsername(username);
        siteApprovalService.updateSiteApproval(siteApprovalRqstDto, userId);
        return ResponseEntity.ok().build();
    }
}
