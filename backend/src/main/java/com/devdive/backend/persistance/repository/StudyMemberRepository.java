package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.entities.StudyMemberJpaEntity;
import com.devdive.backend.persistance.entities.compositekey.StudyMembersId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Primary
public interface StudyMemberRepository extends JpaRepository<StudyMemberJpaEntity, StudyMembersId> {

    @Query("SELECT sm.study FROM StudyMemberJpaEntity sm WHERE sm.member.id = :memberId")
    List<StudyJpaEntity> readStudies(@Param("memberId") Long memberId);
}

