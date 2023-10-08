package com.devdive.backend.security.config.builder;

public interface SecurityConfigurer {

    default void init(){

    }
    void config(HttpSecurity http);
}
