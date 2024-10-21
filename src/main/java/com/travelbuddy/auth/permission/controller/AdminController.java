package com.travelbuddy.auth.permission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PreAuthorize("hasAuthority('MANAGE_USERS')")
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/manage-user")
    public ResponseEntity<String> manageUser() {
        return ResponseEntity.ok("Manage user");
    }

    @PreAuthorize("hasAuthority('MANAGE_GROUPS')")
    @PostMapping("/manage-group")
    public ResponseEntity<String> manageGroup() {
        return ResponseEntity.ok("Manage group");
    }

}
