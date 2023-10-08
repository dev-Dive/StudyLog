package com.devdive.backend.security.core;

public interface UserDetailsService<I> {

    UserDetails findMemberByEmail(I mail);
}
