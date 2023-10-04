package com.devdive.backend.security.filter;

public interface UserDetailsService<I,U> {

    U findMemberByEmail(I mail);
}
