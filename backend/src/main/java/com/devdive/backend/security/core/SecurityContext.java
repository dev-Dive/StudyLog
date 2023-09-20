package com.devdive.backend.security.core;

public interface SecurityContext {
    Authentication getAuthentication();

    void setAuthentication(Authentication authentication);
}
