package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.in.SendMailUseCase;
import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SendMailService implements SendMailUseCase {

    private final JavaMailSender sender;
    private final MailContentGenerator generator;
    private final LoadMemberPort loadMemberPort;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Override
    public void sendEmail(String toMail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        helper.setFrom(fromAddress);
        helper.setSubject(generator.generateSubject());
        helper.setTo(toMail);
        setMailContent(toMail, helper);

        sender.send(message);
    }

    private void setMailContent(String toMail, MimeMessageHelper helper) throws MessagingException {
        if (loadMemberPort.isRegisteredMail(toMail)) {
            setLoginMail(toMail, helper);
            return;
        }
        setRegisterMail(toMail, helper);
    }

    private void setLoginMail(String toMail, MimeMessageHelper helper) throws MessagingException {
        helper.setText(generator.generateAuthContent(toMail, "register"), true);
    }

    private void setRegisterMail(String toMail, MimeMessageHelper helper) throws MessagingException {
        helper.setText(generator.generateAuthContent(toMail, "login"), true);
    }
}
