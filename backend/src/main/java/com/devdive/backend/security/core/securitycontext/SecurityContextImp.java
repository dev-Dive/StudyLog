package com.devdive.backend.security.core.securitycontext;

import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.securitycontext.SecurityContext;

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
