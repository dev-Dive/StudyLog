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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class MailAuthController {

    private final SendMailUseCase sendEmailUseCase;

    @PostMapping("/sendMail")
    ResponseEntity<Void> sendMail(@RequestBody @Valid final SendAuthenticationLinkRequest request) throws MessagingException {

        sendEmailUseCase.sendMail(request.getMail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MessagingException.class)
    ResponseEntity<Void> messagingException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @Data
    static class SendAuthenticationLinkRequest{
        @Email
        @NotNull
        String mail;
    }
}
