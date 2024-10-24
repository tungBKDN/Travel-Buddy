package com.travelbuddy.user;

import com.travelbuddy.user.dto.ChgPasswordRqstDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int getUserIdByEmailOrUsername(String emailOrUsername) {
        return userRepository
                .findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new RuntimeException("User with email not found"))
                .getId();
    }

    @Override
    public boolean isUserExists(String emailOrUsername) {
        return userRepository.existsByEmailOrUsername(emailOrUsername, emailOrUsername);
    }

    @Override
    public void changePassword(int userId, ChgPasswordRqstDto chgPasswordRqstDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(chgPasswordRqstDto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(chgPasswordRqstDto.getNewPassword());
        userRepository.save(user);
    }
}
