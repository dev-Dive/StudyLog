package com.devdive.backend.study.config;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.core.DefaultChainFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudySecurityConfig {

    public DefaultChainFilter studySecurityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .antMatcher("/api/v1/studies/**")
                .authorizeRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}
