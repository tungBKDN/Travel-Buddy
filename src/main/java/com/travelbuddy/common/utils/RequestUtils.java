package com.travelbuddy.common.utils;

import com.travelbuddy.admin.AdminService;
import com.travelbuddy.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestUtils {
    private final UserService userService;
    private final AdminService adminService;

    public  int getUserIdCurrentRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("User email: {}", username);

        return userService.getUserIdByEmailOrUsername(username);
    }

    public int getAdminIdCurrentRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("Admin email: {}", username);

        return adminService.getAdminIdByEmail(username);
    }
}
