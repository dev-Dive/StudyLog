package com.devdive.backend.security.authentication.application.port.out;

import com.devdive.backend.security.authentication.domain.User;

public interface SecurityLoadMemberPort {
    User findByMail(String mail);
}
