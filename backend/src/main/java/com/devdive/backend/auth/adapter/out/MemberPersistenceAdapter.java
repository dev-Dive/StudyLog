package com.devdive.backend.auth.adapter.out;

import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import com.devdive.backend.auth.application.port.out.persistence.UpdateMemberPort;
import com.devdive.backend.auth.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements LoadMemberPort, UpdateMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public boolean isRegisteredMail(String mail) {
        return memberRepository.existsByMail(mail);
    }

    @Override
    public boolean register(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.mapToEntity(member);
        memberRepository.save(memberJpaEntity);
        return true;
    }
}
