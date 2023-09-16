package com.devdive.backend.auth.adapter.out;

import com.devdive.backend.auth.domain.Member;
import org.springframework.stereotype.Component;

@Component
class MemberMapper {

    public MemberJpaEntity mapToEntity(Member member) {
        return MemberJpaEntity.builder()
                .mail(member.getMail())
                .build();
    }
}
