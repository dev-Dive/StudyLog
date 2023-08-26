package com.devdive.backend.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailContentGenerator {

    private String subject = "default subject";
    private String text = "default text";

    public String generateSubject() {
        return subject;
    }

    public String generateContent() {
        return text;
    }
}
