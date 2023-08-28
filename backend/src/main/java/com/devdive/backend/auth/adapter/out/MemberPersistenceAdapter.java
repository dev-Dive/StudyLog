package com.devdive.backend.auth.adapter.out;

import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public boolean isRegisteredMail(String mail) {
        return memberRepository.existsByMail(mail);
    }
}
