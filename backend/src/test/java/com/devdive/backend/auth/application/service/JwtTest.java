package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTest {

    @Test
    @DisplayName("Jwt 생성")
    void jwtCreate(){
        String mail = "rhwlgns@naver.com";
        String key = "test";

        JwtProvider jwtProvider = new JwtProvider(key, 1);
        String jwt = jwtProvider.createJwtToken(mail);

        Claims jwtData = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();

        assertTrue(jwtProvider.isValid(jwt));
        assertEquals(mail, jwtData.getSubject());
    }

    @Test
    @DisplayName("토큰만료")
    void jwtExpiration(){
        String mail = "rhwlgns@naver.com";
        String key = "test";

        JwtProvider jwtProvider = new JwtProvider(key, 0);
        String jwt = jwtProvider.createJwtToken(mail);

        assertThrows(ExpiredJwtException.class,()->jwtProvider.isValid(jwt));
    }

}
