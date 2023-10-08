package com.devdive.backend.security.authentication;

public interface Authentication {
    Object getCredentials();
    Object getDetails();
    Object getPrincipal();

    String getAuthorities();
}
