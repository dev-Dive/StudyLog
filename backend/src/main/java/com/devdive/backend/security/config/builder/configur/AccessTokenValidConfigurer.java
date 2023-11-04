package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.cache.InMemoryAuthenticationCache;
import com.devdive.backend.security.filter.AccessTokenValidationAndRevocationFilter;

public class AccessTokenValidConfigurer extends AbstractSecurityConfigurer {

    private JwtProvider jwtProvider;
    private AuthenticationCache authenticationCache;

    public AccessTokenValidConfigurer(AuthenticationCache authenticationCache) {
        this.authenticationCache = authenticationCache;
    }

    @Override
    public void init() {
        if (jwtProvider == null) {
            throw new IllegalStateException("jwtProvider must be not null");
        }

        if (authenticationCache == null) {
            authenticationCache = InMemoryAuthenticationCache.getInstance();
        }
    }

    @Override
    public void config(HttpSecurity http) {
        http.addFilter(new AccessTokenValidationAndRevocationFilter(jwtProvider, authenticationCache));
    }


    public AccessTokenValidConfigurer accessTokenProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        return this;
    }

    public AccessTokenValidConfigurer accessTokenProvider(String secret, int hour) {
        this.jwtProvider = new JwtProvider(secret, hour);
        return this;
    }
}
