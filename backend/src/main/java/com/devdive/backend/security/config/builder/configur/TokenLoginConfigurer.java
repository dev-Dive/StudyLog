package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.core.*;
import com.devdive.backend.security.core.cache.InMemoryAuthenticationCache;
import com.devdive.backend.security.filter.TokenAuthenticationFilter;

public class TokenLoginConfigurer extends AbstractSecurityConfigurer {

    private UserDetailsService<String> userDetailsService;
    private JwtProvider mailJwtProvider;
    private JwtProvider accessJwtProvider;
    private JwtProvider refreshJwtProvider;

    private String patternUrl = "/api/v1/auth/mail/login";

    private AuthenticationCache authenticationCache;

    @Override
    public void init() {

        if (mailJwtProvider == null) {
            throw new IllegalStateException("mailProvider must be not null");
        }

        if (accessJwtProvider == null) {
            throw new IllegalStateException("accessProvider must be not null");
        }

        if (refreshJwtProvider == null) {
            throw new IllegalStateException("refreshProvider must be not null");
        }

        if (authenticationCache == null) {
            authenticationCache = InMemoryAuthenticationCache.getInstance();
        }
    }

    @Override
    public void config(HttpSecurity http) {
        TokenAuthenticationFilter tokenAuthenticationFilter
                = new TokenAuthenticationFilter(userDetailsService
                , mailJwtProvider
                , accessJwtProvider
                , refreshJwtProvider
                , patternUrl
                , authenticationCache);

        http.addFilter(tokenAuthenticationFilter);
    }

    public TokenLoginConfigurer mvcMatchers(String patternUrl) {
        this.patternUrl = patternUrl;
        return this;
    }

    public TokenLoginConfigurer mailProvider(JwtProvider jwtProvider) {
        this.mailJwtProvider = jwtProvider;
        return this;
    }

    public TokenLoginConfigurer accessTokenProvider(JwtProvider jwtProvider) {
        this.accessJwtProvider = jwtProvider;
        return this;
    }

    public TokenLoginConfigurer refreshTokenProvider(JwtProvider jwtProvider) {
        this.refreshJwtProvider = jwtProvider;
        return this;
    }

    public TokenLoginConfigurer setUserDetailService(UserDetailsService<String> userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }
}
