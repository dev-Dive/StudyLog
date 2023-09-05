package com.devdive.backend.auth.application.port.in;

import jakarta.mail.MessagingException;

public interface SendMailUseCase {

    void sendMail(String email) throws MessagingException;
}
