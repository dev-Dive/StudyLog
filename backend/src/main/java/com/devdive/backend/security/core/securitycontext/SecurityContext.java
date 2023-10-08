package com.devdive.backend.security.core.securitycontext;

import com.devdive.backend.security.authentication.Authentication;

public interface SecurityContext {
    Authentication getAuthentication();

    void setAuthentication(Authentication authentication);
}
