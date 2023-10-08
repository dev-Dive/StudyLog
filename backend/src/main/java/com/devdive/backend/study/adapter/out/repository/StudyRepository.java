package com.devdive.backend.study.adapter.out.repository;

import com.devdive.backend.study.adapter.out.StudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyJpaEntity, Long> {
}
