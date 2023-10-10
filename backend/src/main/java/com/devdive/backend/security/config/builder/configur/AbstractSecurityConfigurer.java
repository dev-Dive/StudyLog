package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.config.builder.SecurityConfigurer;

public abstract class AbstractSecurityConfigurer implements SecurityConfigurer {
    private HttpSecurity builder;

    public HttpSecurity and(){
        return getBuilder();
    }

    public HttpSecurity getBuilder() {
        return builder;
    }

    public void setBuilder(HttpSecurity builder) {
        this.builder = builder;
    }
    public void disable(){
        getBuilder().removeConfigure(getClass());
    }
}
