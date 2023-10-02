package com.devdive.backend.security.core;

public interface AuthenticationCache {
    Authentication findAuthentication(String email);
}
