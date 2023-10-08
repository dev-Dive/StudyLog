package com.devdive.backend.security.core;

public interface AuthenticationCache {
    Authentication findAuthentication(String email);

    void addAuthentication(String key, Authentication authentication);

    void remove(String key);

    void removeAll();
}
