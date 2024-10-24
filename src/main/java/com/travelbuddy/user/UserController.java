package com.travelbuddy.user;

import com.travelbuddy.user.dto.ChgPasswordRqstDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody @Valid ChgPasswordRqstDto chgPasswordRqstDto) {
        int userId = getUserIdCurrentRequest();
        userService.changePassword(userId, chgPasswordRqstDto);
    }

    private int getUserIdCurrentRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("User email: {}", username);

        return userService.getUserIdByEmailOrUsername(username);
    }
}
