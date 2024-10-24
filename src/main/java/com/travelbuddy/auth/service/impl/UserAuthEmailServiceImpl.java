package com.travelbuddy.auth.service.impl;

import com.travelbuddy.persistence.domain.dto.auth.RegisterResponseDto;
import com.travelbuddy.persistence.domain.dto.auth.ResetPasswordRspnDto;
import com.travelbuddy.auth.service.UserAuthEmailService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserAuthEmailServiceImpl implements UserAuthEmailService {
    private final JavaMailSender emailSender;

    private final String fromEmail;

    public UserAuthEmailServiceImpl(JavaMailSender emailSender,
                                    @Value("${spring.mail.username}") String fromEmail) {
        this.emailSender = emailSender;
        this.fromEmail = fromEmail;
    }

    @Override
    public void sendConfirmationEmail(RegisterResponseDto registerResponseDto) {
        String emailContent = "<h1>Chào " + registerResponseDto.getName() + "</h1>" +
                "<p>Mã xác thực OTP của bạn để đăng ký tài khoản TravelBuddy:</p>" +
                "<h2>" + registerResponseDto.getOtp() + "</h2>" +
                "<p>Mã xác thực OTP sẽ hết hạn sau 15 phút</p>";

        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.setFrom(fromEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(registerResponseDto.getEmail()));
            message.setSubject("Kích hoạt tài khoản TravelBuddy");
            message.setText(emailContent, "UTF-8", "html");
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendResetPasswordEmail(ResetPasswordRspnDto resetPasswordRspnDto) {
        String emailContent = "<h1>Chào " + resetPasswordRspnDto.getFullName() + "</h1>" +
                "<p>Mã xác thực OTP của bạn để đặt lại mật khẩu TravelBuddy:</p>" +
                "<h2>" + resetPasswordRspnDto.getOtp() + "</h2>" +
                "<p>Mã xác thực OTP sẽ hết hạn sau 15 phút</p>";

        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.setFrom(fromEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(resetPasswordRspnDto.getEmail()));
            message.setSubject("Đặt lại mật khẩu");
            message.setText(emailContent, "UTF-8", "html");
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
