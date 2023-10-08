package com.devdive.backend.study.adapter.out.persistence.repository;

import com.devdive.backend.study.adapter.out.persistence.MappingTableStudyMemberJpaEntity;
import com.devdive.backend.study.adapter.out.persistence.compositekey.StudyMembersId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingTableStudyMemberRepository extends JpaRepository<MappingTableStudyMemberJpaEntity, StudyMembersId> {
}
