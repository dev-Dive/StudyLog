package com.devdive.backend.security.core;

public class JwtTokenAuthenticationToken implements Authentication {

    private final UserDetails userDetails;
    private String authorities;
    public JwtTokenAuthenticationToken(UserDetails userDetails) {
        this.userDetails =userDetails;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthenticated(String authorities){
        this.authorities=authorities;
    }
}
