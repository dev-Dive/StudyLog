package com.devdive.backend.auth.adapter.in.web;

import com.devdive.backend.auth.application.port.in.SendMailUseCase;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailAuthController {

    private final SendMailUseCase sendEmailUseCase;

    @PostMapping("/sendMail")
    ResponseEntity<Void> sendMail(@RequestBody @Valid final SendAuthenticationLinkRequest request) throws MessagingException {

        sendEmailUseCase.sendEmail(request.getMail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Data
    static class SendAuthenticationLinkRequest{
        @NotNull
        @Email
        String mail;
    }
}
