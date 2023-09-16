package com.devdive.backend.auth.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

    boolean existsByMail(String mail);
}
