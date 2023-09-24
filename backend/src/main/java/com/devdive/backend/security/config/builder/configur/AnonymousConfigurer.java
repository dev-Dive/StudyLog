package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.AnonymousAuthenticationFilter;

public class AnonymousConfigurer extends AbstractSecurityConfigurer{

    @Override
    public void config(HttpSecurity http) {
        http.addFilter(new AnonymousAuthenticationFilter("anonymousUser", "ANONYMOUS"));
    }
}
