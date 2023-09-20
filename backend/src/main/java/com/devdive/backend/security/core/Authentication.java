package com.devdive.backend.security.core;

public interface Authentication {
    Object getCredentials();
    Object getDetails();
    Object getPrincipal();
}
