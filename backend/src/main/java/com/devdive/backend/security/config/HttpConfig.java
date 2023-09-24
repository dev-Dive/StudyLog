package com.devdive.backend.security.config;

import com.devdive.backend.security.config.builder.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpConfig {

    @Bean
    @Scope(scopeName = "prototype")
    public HttpSecurity httpSecurity(){
        HttpSecurity http = new HttpSecurity();
        http.anonymous().and()
                .securityContext().and()
                .exceptionHandling();
        return http;
    }
}
