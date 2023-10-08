package com.devdive.backend.security.authentication.application.service;


import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.authentication.application.port.out.LoadMemberPort;
import com.devdive.backend.security.authentication.application.port.out.LoadMemberPort.UserData;

public class DefaultUserDetailsService<I> implements UserDetailsService<I> {

    private final LoadMemberPort loadMemberPort;

    public DefaultUserDetailsService(LoadMemberPort loadMemberPort) {
        this.loadMemberPort = loadMemberPort;
    }

    @Override
    public UserDetails findMemberByEmail(I mail) {
        UserData userData = loadMemberPort.findByEmail((String) mail);
        if (userData == null) {
            return null;
        }

        return new User(userData.getMail(), userData.getId());
    }
}
