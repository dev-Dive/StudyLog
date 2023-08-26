package com.devdive.backend.auth.application.port.in;

import jakarta.mail.MessagingException;

public interface SendMailUseCase {

    void sendEmail(String email) throws MessagingException;
}
