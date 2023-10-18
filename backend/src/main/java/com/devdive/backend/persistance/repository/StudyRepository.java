package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.StudyJpaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface StudyRepository extends JpaRepository<StudyJpaEntity, Long> {
}
