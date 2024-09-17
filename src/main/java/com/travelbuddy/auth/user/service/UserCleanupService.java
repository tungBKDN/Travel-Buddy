package com.travelbuddy.auth.user.service;

import com.travelbuddy.user.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserCleanupService {
    private static final long ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    private final UserRepository userRepository;

    public UserCleanupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = ONE_DAY_IN_MILLIS)
    @Transactional
    public void deleteUnverifiedUsers() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(1);
        userRepository.deleteUnverifiedUsers(thresholdDate);
    }
}
