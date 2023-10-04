package com.devdive.backend.security.core;

public interface UserDetailsService<I,U> {

    U findMemberByEmail(I mail);
}
