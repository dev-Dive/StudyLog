package com.devdive.backend.study.adapter.out.persistence;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.entities.StudyMemberJpaEntity;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.StudyRepository;
import com.devdive.backend.study.application.dto.StudyCreateDto;
import com.devdive.backend.study.application.port.out.LoadStudyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyPersistenceAdapter implements LoadStudyPort {

    private final MemberRepository memberStudyRepository;
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Override
    public void createStudy(StudyCreateDto dto) {
        MemberJpaEntity member = memberStudyRepository.findById(dto.getMemberId()).orElseThrow(IllegalArgumentException::new);

        StudyJpaEntity studyJpaEntity = new StudyJpaEntity();
        studyJpaEntity.setName(dto.getName());
        studyJpaEntity.setDescription(dto.getDescription());

        studyRepository.save(studyJpaEntity);

        StudyMemberJpaEntity mappingTable = new StudyMemberJpaEntity();
        mappingTable.setMember(member);
        mappingTable.setStudy(studyJpaEntity);

        studyMemberRepository.save(mappingTable);
    }
}
