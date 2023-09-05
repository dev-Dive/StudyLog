package com.devdive.backend.auth.application.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final String secret;
    private final int expireJwtTime;

    public JwtProvider(@Value("${auth.jwt.secret}") String secret,
                       @Value("${auth.jwt.hour}") int expireJwtHour) {
        this.secret = secret;
        this.expireJwtTime = 1_000 * 60 * 60 * expireJwtHour;
    }

    public String createJwtToken(String subject) {
        Date iat = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .setSubject(subject)
                .setHeader(createHeader())
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + expireJwtTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    public boolean isValid(String jwt) {
        Claims jwtData = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt).getBody();

        return jwtData.getSubject() != null;
    }
}
