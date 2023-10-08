package com.devdive.backend.security.core;

public class User implements UserDetails {

    private final String mail;

    private final Long id;

    public User(String mail, Long id) {
        this.mail = mail;
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return mail;
    }
}
