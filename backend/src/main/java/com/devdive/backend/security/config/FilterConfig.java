package com.devdive.backend.security.config;

import com.devdive.backend.security.filter.FilterChainProxy;
import com.devdive.backend.security.filter.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class FilterConfig {

    @Bean(name = "securityFilter")
    public FilterChainProxy filterChainProxy(List<SecurityFilterChain> securityFilterChains){
        return new FilterChainProxy(securityFilterChains);
    }
}
