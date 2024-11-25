package com.travelbuddy.siteapproval.admin;

import com.travelbuddy.auth.service.AdminService;
import com.travelbuddy.persistence.domain.dto.siteapproval.UpdateSiteApprovalRqstDto;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('MANAGE_SITES')")
    @GetMapping
    // Get and paging for all site approvals
    public ResponseEntity<Object> getAllSiteApprovals(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(siteApprovalService.getPendingSiteApprovals(page));
    }
}
