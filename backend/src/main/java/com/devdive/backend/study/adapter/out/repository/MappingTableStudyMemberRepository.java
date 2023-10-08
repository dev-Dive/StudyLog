package com.devdive.backend.study.adapter.out.repository;

import com.devdive.backend.study.adapter.out.MappingTableStudyMemberJpaEntity;
import com.devdive.backend.study.adapter.out.compositekey.StudyMembersId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingTableStudyMemberRepository extends JpaRepository<MappingTableStudyMemberJpaEntity, StudyMembersId> {
}
