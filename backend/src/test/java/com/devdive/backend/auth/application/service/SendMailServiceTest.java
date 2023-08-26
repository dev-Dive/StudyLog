package com.devdive.backend.auth.application.service;

import groovy.util.logging.Slf4j;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class SendMailServiceTest {

    @Autowired
    private SendMailService sendMailService;

    @Test
    void sendEmail() throws MessagingException {
        sendMailService.sendEmail("qkfka9045@gmail.com");
    }
}