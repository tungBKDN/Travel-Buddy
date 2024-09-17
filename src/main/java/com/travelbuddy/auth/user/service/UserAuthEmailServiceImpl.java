package com.travelbuddy.auth.user.service;

import com.travelbuddy.auth.user.dto.RegisterTokenDto;
import com.travelbuddy.auth.user.dto.ResetPasswordTokenDto;
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

    private final String appUrl;

    private final String fromEmail;

    public UserAuthEmailServiceImpl(@Value("${app.url}") String appUrl,
                                    @Value("${spring.mail.username}") String fromEmail,
                                    JavaMailSender emailSender) {
        this.appUrl = appUrl;
        this.fromEmail = fromEmail;
        this.emailSender = emailSender;
    }

    @Override
    public void sendConfirmationEmail(RegisterTokenDto registerTokenDto) {
        String emailContent = "<h1>Chào " + registerTokenDto.getName() + "</h1>" +
                "<p>Vui lòng nhấp vào liên kết sau để kích hoạt tài khoản của bạn:</p>" +
                "<a href=\"" + appUrl + "/confirm-registration/" + registerTokenDto.getToken() + "\">Kích hoạt tài khoản</a>";

        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.setFrom(fromEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(registerTokenDto.getEmail()));
            message.setSubject("Kích hoạt tài khoản");
            message.setText(emailContent, "UTF-8", "html");
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendResetPasswordEmail(ResetPasswordTokenDto resetPasswordTokenDto) {
        String emailContent = "<h1>Chào " + resetPasswordTokenDto.getName() + "</h1>" +
                "<p>Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu của bạn:</p>" +
                "<a href=\"" + appUrl + "/reset-password?" + "userId=" + resetPasswordTokenDto.getUserId() + "&token=" + resetPasswordTokenDto.getToken() + "\">Đặt lại mật khẩu</a>";

        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.setFrom(fromEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(resetPasswordTokenDto.getEmail()));
            message.setSubject("Đặt lại mật khẩu");
            message.setText(emailContent, "UTF-8", "html");
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
