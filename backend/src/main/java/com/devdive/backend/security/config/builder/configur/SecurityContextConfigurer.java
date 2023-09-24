package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.SecurityContextPersistenceFilter;

public class SecurityContextConfigurer extends AbstractSecurityConfigurer{
    @Override
    public void config(HttpSecurity http) {
        http.addFilter(new SecurityContextPersistenceFilter());
    }
}
