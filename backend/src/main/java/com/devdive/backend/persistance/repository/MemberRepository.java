package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
@Primary
public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

    boolean existsByMail(String mail);
}
