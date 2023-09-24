package com.devdive.backend.security.config.builder;

import com.devdive.backend.security.filter.CorsFilter;

public class CorsConfigurer implements SecurityConfigurer {
    @Override
    public void config(HttpSecurity http) {
        http.addFilter(new CorsFilter());
    }
}
