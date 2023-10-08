package com.devdive.backend.post.adapter.out.persistence.repository;

import com.devdive.backend.post.adapter.out.persistence.MemberPostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPostRepository extends JpaRepository<MemberPostJpaEntity, Long> {
}
