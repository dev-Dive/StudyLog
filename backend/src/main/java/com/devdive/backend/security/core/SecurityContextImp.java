package com.devdive.backend.security.core;

public class SecurityContextImp implements SecurityContext {

    private Authentication authentication;
    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication=authentication;
    }
}
