package com.devdive.backend.security.authentication.application.service;


import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.authentication.application.port.out.SecurityLoadMemberPort;

public class DefaultUserDetailsService<I> implements UserDetailsService<I> {

    private final SecurityLoadMemberPort securityLoadMemberPort;

    public DefaultUserDetailsService(SecurityLoadMemberPort securityLoadMemberPort) {
        this.securityLoadMemberPort = securityLoadMemberPort;
    }

    @Override
    public UserDetails findMemberByEmail(I mail) {
        User userData = securityLoadMemberPort.findByMail((String) mail);
        if (userData == null) {
            return null;
        }

        return userData;
    }
}
