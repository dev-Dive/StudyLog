package com.devdive.backend.security.authentication.application.port.in;

import com.devdive.backend.security.authentication.domain.UserDetails;

public interface UserDetailsService<I> {

    UserDetails findMemberByEmail(I mail);
}
