package com.devdive.backend.security.core;


import com.devdive.backend.security.core.LoadMemberPort.UserData;

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
