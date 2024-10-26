package com.travelbuddy.auth.service.impl;

import com.travelbuddy.common.constants.RoleEnum;
import com.travelbuddy.common.exception.auth.InvalidLoginCredentialsException;
import com.travelbuddy.auth.token.jwt.JWTProcessor;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.common.exception.userinput.InvaidOtpException;
import com.travelbuddy.persistence.domain.dto.auth.*;
import com.travelbuddy.persistence.domain.entity.TokenStoreEntity;
import com.travelbuddy.persistence.repository.TokenStoreRepository;
import com.travelbuddy.mapper.UserMapper;
import com.travelbuddy.auth.service.UserAuthEmailService;
import com.travelbuddy.auth.service.UserAuthRedisService;
import com.travelbuddy.auth.service.UserAuthService;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import com.travelbuddy.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;

    private final JWTProcessor jwtProcessor;

    private final PasswordEncoder passwordEncoder;

    private final UserAuthEmailService userAuthEmailService;

    private final UserAuthRedisService userAuthRedisService;

    private final UserMapper userMapper;

    private final TokenStoreRepository tokenStoreRepository;

    @Override
    public LoginRspnDto login(LoginRqstDto loginRqstDto) {
        String emailOrUsername = loginRqstDto.getEmailOrUsername();
        String password = loginRqstDto.getPassword();

        UserEntity user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(InvalidLoginCredentialsException::new);

        if (!passwordEncoder.matches(password, user.getPassword()) || !user.isEnabled()) {
            throw new InvalidLoginCredentialsException();
        }

        String accessToken = jwtProcessor.getBuilder()
                .withSubject(user.getEmail())
                .withScopes(List.of("ROLE_"+ RoleEnum.USER.name()))
                .build();

        String refreshToken = jwtProcessor.getBuilder(true)
                .withSubject(user.getEmail())
                .withScopes(List.of("ROLE_"+ RoleEnum.USER.name()))
                .build();

        tokenStoreRepository.save(TokenStoreEntity.builder()
                .token(refreshToken)
                .userId(user.getId())
                .build());

        return LoginRspnDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .basicInfo(userMapper.toBasicInfoDto(user))
                .build();
    }

    @Override
    public void register(RegisterRqstDto registerRqstDTO) {
        UserEntity user = new UserEntity();
        user.setEmail(registerRqstDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRqstDTO.getPassword()));
        user.setFullName(registerRqstDTO.getFullName());

        userRepository.save(user);

        String otp = generateOtp();
        userAuthRedisService.save("otp:" + user.getId(), otp, 15, TimeUnit.MINUTES);
        userAuthRedisService.save("otp_attempts:" + user.getId(), 0, 15, TimeUnit.MINUTES);
        userAuthRedisService.expire("otp_attempts:" + user.getId(), 15, TimeUnit.MINUTES);

        userAuthEmailService.sendConfirmationEmail(RegisterResponseDto.builder()
                .name(user.getFullName())
                .email(user.getEmail())
                .otp(otp)
                .build());
    }

    @Override
    public void confirmRegistration(VerificationOtpRqstDto verificationOtpRqstDto) {
        UserEntity user = userRepository.findByEmailOrUsername(verificationOtpRqstDto.getEmail(), verificationOtpRqstDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Long attempts = userAuthRedisService.increment("otp_attempts:" + user.getId(), 1);

        if (attempts > 3) {
            userAuthRedisService.delete("otp_attempts:" + user.getId());
            userAuthRedisService.delete("otp:" + user.getId());
            throw new InvaidOtpException("Too many attempts");
        }

        String otp = userAuthRedisService.get("otp:" + user.getId());
        if (!otp.equals(verificationOtpRqstDto.getOtp())) {
            throw new InvaidOtpException("Invalid OTP");
        }

        userAuthRedisService.delete("otp_attempts:" + user.getId());
        userAuthRedisService.delete("otp:" + user.getId());

        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(ResetPasswordRqstDto resetPasswordRqstDto) {
        UserEntity user = userRepository.findByEmailOrUsername(resetPasswordRqstDto.getEmail(), resetPasswordRqstDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String otp = generateOtp();
        userAuthRedisService.save("otp_rs_pw:" + user.getId(), otp, 15, TimeUnit.MINUTES);
        userAuthRedisService.save("otp_rs_pw_attempts:" + user.getId(), 0, 15, TimeUnit.MINUTES);
        userAuthRedisService.expire("otp_rs_pw_attempts:" + user.getId(), 15, TimeUnit.MINUTES);

        userAuthEmailService.sendResetPasswordEmail(ResetPasswordRspnDto.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .otp(otp)
                .build());
    }

    @Override
    public void confirmNewPassword(ConfirmNewPasswordRqstDto confirmNewPasswordTokenDto) {
        UserEntity user = userRepository.findByEmailOrUsername(confirmNewPasswordTokenDto.getEmail(), confirmNewPasswordTokenDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Long attempts = userAuthRedisService.increment("otp_rs_pw_attempts:" + user.getId(), 1);

        if (attempts > 3) {
            userAuthRedisService.delete("otp_rs_pw_attempts:" + user.getId());
            userAuthRedisService.delete("otp_rs_pw:" + user.getId());
            throw new InvaidOtpException("Too many attempts");
        }

        String otp = userAuthRedisService.get("otp_rs_pw:" + user.getId());
        if (!otp.equals(confirmNewPasswordTokenDto.getOtp())) {
            throw new InvaidOtpException("Invalid OTP");
        }

        userAuthRedisService.delete("otp_rs_pw_attempts:" + user.getId());
        userAuthRedisService.delete("otp_rs_pw:" + user.getId());

        user.setPassword(passwordEncoder.encode(confirmNewPasswordTokenDto.getNewPassword()));
        userRepository.save(user);

    }

    @Override
    public BasicInfoDto getUserBasicInfo(String emailOrUsername) {
        UserEntity user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.toBasicInfoDto(user);
    }

    @Override
    public String refreshToken(int userId) {
        return jwtProcessor.getBuilder()
                .withSubject(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")).getEmail())
                .withScopes(List.of("ROLE_"+ RoleEnum.USER.name()))
                .build();
    }

    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
