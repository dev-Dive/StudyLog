package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtProviderTest {

    private static final String BASE64_ENCODED_SECRET = "OTddTYzSY0R+fPLJKpJph4XTEFiPWi0Wfl3UT0JsHQg";

    @Test
    @DisplayName("유효한 JWT 생성")
    void jwtCreate() {
        String mail = "rhwlgns@naver.com";

        JwtProvider jwtProvider = new JwtProvider(BASE64_ENCODED_SECRET, 1);
        String jwt = jwtProvider.createJwtToken(mail);

        assertTrue(jwtProvider.isValid(jwt));
    }

    @Test
    @DisplayName("토큰만료")
    void jwtExpiration() {
        String mail = "rhwlgns@naver.com";

        JwtProvider jwtProvider = new JwtProvider(BASE64_ENCODED_SECRET, 0);
        String jwt = jwtProvider.createJwtToken(mail);

        assertFalse(jwtProvider.isValid(jwt));
    }

    @Test
    @DisplayName("유효하지 않은 시그니처")
    void givenInvalidSignature_whenValid_thenThrowException() {
        String mail = "test@test.com";

        JwtProvider jwtProvider = new JwtProvider(BASE64_ENCODED_SECRET, 1);
        String jwt = jwtProvider.createJwtToken(mail) + "1";

        assertFalse(jwtProvider.isValid(jwt));
    }

    @Test
    @DisplayName("JWT에서 sub 추출")
    void givenJwt_whenExtractSub_thenGetSub() {
        String mail = "test@test.com";

        JwtProvider jwtProvider = new JwtProvider(BASE64_ENCODED_SECRET, 1);
        String jwt = jwtProvider.createJwtToken(mail);

        assertEquals(jwtProvider.extractSubject(jwt), mail);
    }

}
