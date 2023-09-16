package com.devdive.backend.auth.application.port.in;

import lombok.Data;

public interface RegisterUseCase {

    AuthToken register(String token);

    @Data
    class AuthToken {
        private final String accessToken;
        private final String refreshToken;
    }
}
