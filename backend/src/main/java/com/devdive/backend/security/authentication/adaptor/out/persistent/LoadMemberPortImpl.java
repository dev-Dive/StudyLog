package com.devdive.backend.security.authentication.adaptor.out.persistent;

import com.devdive.backend.security.authentication.application.port.out.LoadMemberPort;

public class LoadMemberPortImpl implements LoadMemberPort {

    private final UserDataRepository userDataRepository;

    public LoadMemberPortImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserData findByEmail(String mail) {
        return userDataRepository.findByMail(mail);
    }
}
