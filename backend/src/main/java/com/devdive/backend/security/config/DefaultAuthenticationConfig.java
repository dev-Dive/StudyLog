package com.devdive.backend.security.config;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.DefaultChainFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultAuthenticationConfig {
    @Bean
    public DefaultChainFilter defaultChainFilter(HttpSecurity http){
        http.authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
}
