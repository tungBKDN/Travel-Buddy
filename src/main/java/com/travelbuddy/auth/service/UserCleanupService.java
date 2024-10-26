package com.travelbuddy.auth.service;

import com.travelbuddy.persistence.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserCleanupService {
    private final long MAIL_VERIFY_EXPIRED_TIME_MINUTES = 15 * 60 * 1000;

    private final UserRepository userRepository;

    public UserCleanupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = MAIL_VERIFY_EXPIRED_TIME_MINUTES)
    @Transactional
    public void deleteUnverifiedUsers() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusMinutes(15);
        userRepository.deleteUnverifiedUsers(thresholdDate);
    }
}
