package com.devdive.backend.auth.config;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    private static final int ONE_WEEK = 24 * 7;

    @Bean
    public JwtProvider mailJwtProvider(
            @Value("${jwt.mail.secret}") String secret,
            @Value("${jwt.mail.hour}") int hour) {
        return new JwtProvider(secret, hour);
    }

    @Bean
    public JwtProvider accessJwtProvider(
            @Value("${jwt.auth.access.secret}") String secret,
            @Value("${jwt.auth.access.hour}") int hour) {
        return new JwtProvider(secret, hour);
    }

    @Bean
    public JwtProvider refreshJwtProvider(
            @Value("${jwt.auth.refresh.secret}") String secret,
            @Value("${jwt.auth.refresh.week}") int week) {
        return new JwtProvider(secret, ONE_WEEK * week);
    }
}
