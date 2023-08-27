package com.devdive.backend.auth.application.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtProvider {

    public String secret;
    public int expireJwtTime;

    public JwtProvider(JwtProperties properties) {
        secret = properties.secret;
        expireJwtTime = properties.expireJwtTime;
    }

    public String createJwtToken(String mail) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(mail)
                .setHeader(createHeader())
                .setClaims(createClaims(mail)) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + expireJwtTime)) // 만료일
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256"); // 해시 256 사용하여 암호화
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private Map<String, Object> createClaims(String mail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("mail", mail);
        return claims;
    }

    public boolean isValid(String jwt){
        Claims jwtData = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt).getBody();

        return jwtData.get("mail") != null;
    }
}
