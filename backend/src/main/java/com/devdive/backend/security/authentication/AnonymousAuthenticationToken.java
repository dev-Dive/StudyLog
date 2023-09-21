package com.devdive.backend.security.authentication;

import com.devdive.backend.security.core.Authentication;

public class AnonymousAuthenticationToken implements Authentication {
    private Object principal;
    private String authorities;
    public AnonymousAuthenticationToken(Object principal, String authorities) {
        this.principal=principal;
        this.authorities=authorities;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public String getAuthorities() {
        return authorities;
    }
}
