package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.PostMemberJpaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Primary
public interface PostMemberRepository extends JpaRepository<PostMemberJpaEntity, Long> {

    List<PostMemberJpaEntity> findByMember_Id(Long memberId);

    List<PostMemberJpaEntity> findByPostId(Long postId);
}
