package com.devdive.backend.auth.adapter.in.web;

import com.devdive.backend.auth.application.port.in.RegisterUseCase;
import com.devdive.backend.auth.application.port.in.SendMailUseCase;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/mail")
@RequiredArgsConstructor
class MailAuthController {

    private final SendMailUseCase sendEmailUseCase;
    private final RegisterUseCase registerUseCase;

    @PostMapping("/sendMail")
    ResponseEntity<Void> sendMail(@RequestBody @Valid final SendAuthenticationLinkRequest request) throws MessagingException {

        sendEmailUseCase.sendMail(request.getMail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<AuthenticateResponse> register(
            @RequestBody @Valid AuthenticateRequest request
    ) {
        log.info("Auth token: {}", request.getToken());
        RegisterUseCase.AuthToken authToken = registerUseCase.register(request.getToken());
        String accessToken = authToken.getAccessToken();
        String refreshToken = authToken.getRefreshToken();

        AuthenticateResponse response = new AuthenticateResponse(accessToken, refreshToken);

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MessagingException.class)
    ResponseEntity<Void> messagingException(MessagingException e) {
        log.error("MessagingException", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Void> invalidToken(IllegalArgumentException e) {
        log.info(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }


    @Data
    static class SendAuthenticationLinkRequest{
        @Email
        @NotNull
        String mail;
    }

    @Data
    static class AuthenticateRequest {
        @NotBlank
        String token;
    }

    @Data
    static class AuthenticateResponse {
        private final String accessToken;
        private final String refreshToken;
    }
}
