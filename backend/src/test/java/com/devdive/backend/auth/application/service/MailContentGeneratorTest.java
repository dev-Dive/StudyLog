package com.devdive.backend.auth.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MailContentGeneratorTest {

    @Autowired
    private MailContentGenerator mailContentGenerator;

    @Test
    @DisplayName("회원가입 인증 링크 생성 시 api url이 메일 컨텐츠에 포함된다.")
    void givenMailAndRequest_whenGenerateMailContent_thenContainUrlPath(){
        String mail = "rkdtjdqja@gmail.com";
        String request = "register";

        String content = mailContentGenerator.generateAuthContent(mail, request);

        assertThat(content).contains("/api/v1/register");
    }

}
