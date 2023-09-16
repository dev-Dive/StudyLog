package com.devdive.backend.auth.application.port.out.persistence;

import com.devdive.backend.auth.domain.Member;

public interface UpdateMemberPort {
    boolean register(Member member);
}
