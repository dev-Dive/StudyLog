package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class MailContentGenerator {

    private final String MAIL_FORMAT = "<button onclick=\'\"window.open(\'%s\')\">button</button> <br/> %s";

    private final String subject = "StudyLog 운영팀";

    private final JwtProvider mailJwtProvider;

    public String generateSubject() {
        return subject;
    }

    public String generateAuthContent(String mail, String request) {
        String jwt = mailJwtProvider.createJwtToken(mail);

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.dev-dive.com")
                .path(request)
                .queryParam("token", jwt)
                .build(true)
                .toUriString();

        return String.format(MAIL_FORMAT, uri, uri);
    }
}
