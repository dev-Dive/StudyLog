package com.devdive.backend.security.authentication.adaptor.out.persistent;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.security.authentication.application.port.out.SecurityLoadMemberPort;
import com.devdive.backend.security.authentication.domain.User;

public class LoadMemberPortImpl implements SecurityLoadMemberPort {

    private final UserDataRepository userDataRepository;

    public LoadMemberPortImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public User findByMail(String mail) {
        MemberJpaEntity member = userDataRepository.findByMail(mail);
        if (member == null) {
            return null;
        }
        return new User(member.getMail(), member.getId());
    }
}
