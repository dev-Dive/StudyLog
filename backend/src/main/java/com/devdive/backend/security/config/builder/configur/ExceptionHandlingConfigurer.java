package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.ExceptionTranslationFilter;

public class ExceptionHandlingConfigurer extends AbstractSecurityConfigurer {
    @Override
    public void config(HttpSecurity http) {
        http.addFilter(new ExceptionTranslationFilter());
    }
}
