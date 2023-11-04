package com.devdive.backend.study2.adapter.out;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.entities.StudyMemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.persistance.repository.StudyRepository;
import com.devdive.backend.study2.application.port.out.CreateStudyPort;
import com.devdive.backend.study2.domain.Person;
import com.devdive.backend.study2.domain.Role;
import com.devdive.backend.study2.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
class StudyPersistenceJpaAdapter implements CreateStudyPort {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyMapper studyMapper;

    @Override
    public void save(Study study) {
        List<Long> memberIds = studyMapper.toIds(study);
        List<MemberJpaEntity> memberJpaEntities = memberRepository.findAllById(memberIds);
        StudyJpaEntity studyJpaEntity = studyMapper.mapToStudyJpaEntity(study);

        studyRepository.save(studyJpaEntity);
        study.changeId(studyJpaEntity.getId());

        studyMemberRepository.saveAll(mapToStudyMemberJpaEntities(studyMapper.toRoleByIds(study), studyJpaEntity, memberJpaEntities));
    }

    private List<StudyMemberJpaEntity> mapToStudyMemberJpaEntities(
            Map<Person.PersonId
            , Role> roleByIds, StudyJpaEntity study
            , List<MemberJpaEntity> memberJpaEntities) {
        List<StudyMemberJpaEntity> studyMemberJpaEntities = new ArrayList<>();
        for (MemberJpaEntity memberJpaEntity : memberJpaEntities) {
            StudyMemberJpaEntity studyMemberJpaEntity = new StudyMemberJpaEntity();
            studyMemberJpaEntity.setMember(memberJpaEntity);
            studyMemberJpaEntity.setRole(roleByIds.get(new Person.PersonId(memberJpaEntity.getId())).name());
            studyMemberJpaEntity.setStudy(study);

            studyMemberJpaEntities.add(studyMemberJpaEntity);
        }
        return studyMemberJpaEntities;
    }

}
