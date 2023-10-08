package com.devdive.backend.security.core;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAuthenticationCache implements AuthenticationCache {

    private static final ConcurrentHashMap<String, Authentication> store = new ConcurrentHashMap<>();
    private static final AuthenticationCache instance = new InMemoryAuthenticationCache();

    private InMemoryAuthenticationCache() {

    }

    public static AuthenticationCache getInstance() {
        return instance;
    }

    @Override
    public Authentication findAuthentication(String key) {
        return store.get(key);
    }

    @Override
    public void addAuthentication(String key, Authentication authentication) {
        store.put(key, authentication);
    }

    @Override
    public void remove(String key) {
        store.remove(key);
    }

    @Override
    public void removeAll() {
        store.clear();
    }

}
