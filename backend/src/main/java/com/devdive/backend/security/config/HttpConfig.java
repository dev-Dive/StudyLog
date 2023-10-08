package com.devdive.backend.security.config;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.authentication.application.port.out.LoadMemberPort;
import com.devdive.backend.security.authentication.adaptor.out.persistent.LoadMemberPortImpl;
import com.devdive.backend.security.authentication.application.service.DefaultUserDetailsService;
import com.devdive.backend.security.authentication.adaptor.out.persistent.UserDataRepository;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.config.builder.HttpSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpConfig {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private JwtProvider mailJwtProvider;

    @Bean
    @Scope(scopeName = "prototype")
    public HttpSecurity httpSecurity(){
        HttpSecurity http = new HttpSecurity(userDetailsService(loadMemberPort()));
        http.cors().anonymous().and()
                .securityContext().and().accessTokenValid().accessTokenProvider(mailJwtProvider).and()
                .exceptionHandling();
        return http;
    }

    @Bean
    public UserDetailsService<String> userDetailsService(LoadMemberPort loadMemberPort){
        return new DefaultUserDetailsService<>(loadMemberPort);
    }

    @Bean
    public LoadMemberPort loadMemberPort(){
        return new LoadMemberPortImpl(userDataRepository);
    }
}
