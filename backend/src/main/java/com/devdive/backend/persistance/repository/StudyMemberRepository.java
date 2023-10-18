package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.StudyMemberJpaEntity;
import com.devdive.backend.persistance.entities.compositekey.StudyMembersId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface StudyMemberRepository extends JpaRepository<StudyMemberJpaEntity, StudyMembersId> {
}
