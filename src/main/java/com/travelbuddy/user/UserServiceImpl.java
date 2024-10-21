package com.travelbuddy.user;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getUserIdByEmailOrUsername(String emailOrUsername) {
        return userRepository
                .findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new RuntimeException("User with email not found"))
                .getId();
    }
}
