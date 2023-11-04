package com.devdive.backend.security.config;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.authentication.application.port.out.SecurityLoadMemberPort;
import com.devdive.backend.security.authentication.adaptor.out.persistent.LoadMemberPortImpl;
import com.devdive.backend.security.authentication.application.service.DefaultUserDetailsService;
import com.devdive.backend.security.authentication.adaptor.out.persistent.UserDataRepository;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.core.AuthenticationCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpConfig {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private JwtProvider accessJwtProvider;

    @Autowired
    private AuthenticationCache authenticationCache;

    @Bean
    @Scope(scopeName = "prototype")
    public HttpSecurity httpSecurity() {
        HttpSecurity http = new HttpSecurity(userDetailsService(securityLoadMemberPort()), authenticationCache);
        http.cors().anonymous().and()
                .securityContext().and().accessTokenValid()
                .accessTokenProvider(accessJwtProvider).and()
                .exceptionHandling();
        return http;
    }

    @Bean
    public UserDetailsService<String> userDetailsService(SecurityLoadMemberPort securityLoadMemberPort) {
        return new DefaultUserDetailsService<>(securityLoadMemberPort);
    }

    @Bean
    public SecurityLoadMemberPort securityLoadMemberPort() {
        return new LoadMemberPortImpl(userDataRepository);
    }
}
