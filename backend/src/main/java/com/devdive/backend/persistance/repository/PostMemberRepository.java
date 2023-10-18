package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.PostMemberJpaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface PostMemberRepository extends JpaRepository<PostMemberJpaEntity, Long> {
}
