package com.devdive.backend.security.authentication.adaptor.out.persistent;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDataRepository extends MemberRepository {

    MemberJpaEntity findByMail(String mail);
}
