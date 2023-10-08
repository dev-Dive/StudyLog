package com.devdive.backend.study.adapter.out.repository;

import com.devdive.backend.study.adapter.out.MemberStudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyRepository extends JpaRepository<MemberStudyJpaEntity, Long> {
}
