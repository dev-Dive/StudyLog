package com.devdive.backend.study.adapter.out.persistence.repository;

import com.devdive.backend.study.adapter.out.persistence.MemberStudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyRepository extends JpaRepository<MemberStudyJpaEntity, Long> {
}
