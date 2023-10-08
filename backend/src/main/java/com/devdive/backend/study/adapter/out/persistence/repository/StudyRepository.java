package com.devdive.backend.study.adapter.out.persistence.repository;

import com.devdive.backend.study.adapter.out.persistence.StudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyJpaEntity, Long> {
}
