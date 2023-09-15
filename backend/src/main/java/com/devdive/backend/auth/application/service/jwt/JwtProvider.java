package com.devdive.backend.auth.application.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtProvider {

    private static final int DAY = 1_000 * 60 * 60;

    private final String secret;
    private final int expireJwtTime;

    public JwtProvider(String secret, int expireJwtHour) {
        this.secret = secret;
        this.expireJwtTime = DAY * expireJwtHour;
    }

    public String createJwtToken(String subject) {
        Date iat = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + expireJwtTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT is Expired");
            return false;
        } catch (JwtException e) {
            log.warn("ex", e);
            return false;
        }
    }

    public String extractSubject(String jwt) {
        return extractClaims(jwt).getSubject();
    }

    private Claims extractClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyByte = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
