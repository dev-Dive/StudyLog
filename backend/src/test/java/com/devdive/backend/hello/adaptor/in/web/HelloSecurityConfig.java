package com.devdive.backend.hello.adaptor.in.web;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.DefaultChainFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@TestConfiguration
public class HelloSecurityConfig {

    @Autowired
    private JwtProvider mailJwtProvider;

    @Autowired
    private JwtProvider accessJwtProvider;

    @Autowired
    private JwtProvider refreshJwtProvider;

    @Bean
    @Order(10)
    public DefaultChainFilter helloFilterChain(HttpSecurity httpSecurity){
        httpSecurity.antMatcher("/hello/**")
                .accessTokenValid()
                .accessTokenProvider(accessJwtProvider)
                .and()
                .authorizeRequests()
                .mvcMatchers("/hello/admin").hasRole("ADMIN")
                .mvcMatchers("/hello")
                .authenticated();

        return httpSecurity.build();
    }

    @Bean
    @Order(1)
    public DefaultChainFilter login(HttpSecurity httpSecurity){
        httpSecurity
                .antMatcher("/api/v1/auth/mail/login/**")
                .tokenLogin()
                .refreshTokenProvider(refreshJwtProvider)
                .accessTokenProvider(accessJwtProvider)
                .mailProvider(mailJwtProvider);

        return httpSecurity.build();
    }
}
