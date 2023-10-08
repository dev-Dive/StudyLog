package com.devdive.backend.security.config;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.FilterChainProxy;
import com.devdive.backend.security.core.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class FilterConfig {

    @Autowired
    private JwtProvider mailJwtProvider;

    @Autowired
    private JwtProvider accessJwtProvider;

    @Autowired
    private JwtProvider refreshJwtProvider;

    @Bean(name = "securityFilter")
    public FilterChainProxy filterChainProxy(HttpSecurity http, List<SecurityFilterChain> securityFilterChains){
        if(securityFilterChains.size()==0){
            http.tokenLogin()
                    .mailProvider(mailJwtProvider)
                    .accessTokenProvider(accessJwtProvider)
                    .refreshTokenProvider(refreshJwtProvider)
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
            securityFilterChains.add(http.build());
        }
        return new FilterChainProxy(securityFilterChains);
    }
}
