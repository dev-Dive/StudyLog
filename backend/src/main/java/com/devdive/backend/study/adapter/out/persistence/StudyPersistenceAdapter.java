package com.devdive.backend.study.adapter.out.persistence;

import com.devdive.backend.study.adapter.out.persistence.repository.MappingTableStudyMemberRepository;
import com.devdive.backend.study.adapter.out.persistence.repository.MemberStudyRepository;
import com.devdive.backend.study.adapter.out.persistence.repository.StudyRepository;
import com.devdive.backend.study.application.dto.StudyCreateDto;
import com.devdive.backend.study.application.port.out.LoadStudyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyPersistenceAdapter implements LoadStudyPort {

    private final MemberStudyRepository memberStudyRepository;
    private final StudyRepository studyRepository;
    private final MappingTableStudyMemberRepository mappingTableStudyMemberRepository;

    @Override
    public void createStudy(StudyCreateDto dto) {
        MemberStudyJpaEntity member = memberStudyRepository.findById(dto.getMemberId()).orElseThrow(IllegalArgumentException::new);

        StudyJpaEntity studyJpaEntity = new StudyJpaEntity();
        studyJpaEntity.setName(dto.getName());
        studyJpaEntity.setDescription(dto.getDescription());

        studyRepository.save(studyJpaEntity);

        StudyMemberJpaEntity mappingTable  = new StudyMemberJpaEntity();
        mappingTable.setMember(member);
        mappingTable.setStudy(studyJpaEntity);

        mappingTableStudyMemberRepository.save(mappingTable);
    }
}
