package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import groovy.util.logging.Slf4j;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.when;


@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class SendMailServiceTest {

    @Autowired
    private SendMailService sendMailService;

    @MockBean
    private LoadMemberPort loadMemberPort;

    @Test
    @Disabled
    @DisplayName("이미 회원가입된 메일로 인증 요청 시 로그인 링크가 전송된다.")
    void requestLogin() throws MessagingException {
        String mail = "qkfka9045@gmail.com";

        when(loadMemberPort.isRegisteredMail(mail)).thenReturn(true);

        sendMailService.sendEmail(mail);
    }

    @Test
    @Disabled
    @DisplayName("이미 회원가입된 메일로 인증 요청 시 로그인 링크가 전송된다.")
    void requestRegister() throws MessagingException {
        String mail = "qkfka9045@gmail.com";

        when(loadMemberPort.isRegisteredMail(mail)).thenReturn(false);

        sendMailService.sendEmail(mail);
    }
}