package com.devdive.backend.study.adapter.out;

import com.devdive.backend.study.adapter.out.repository.MappingTableStudyMemberRepository;
import com.devdive.backend.study.adapter.out.repository.MemberStudyRepository;
import com.devdive.backend.study.adapter.out.repository.StudyRepository;
import com.devdive.backend.study.application.dto.StudyCreateDto;
import com.devdive.backend.study.application.port.out.persistence.LoadStudyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudyPersistenceAdapter implements LoadStudyPort {

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

        MappingTableStudyMemberJpaEntity mappingTable  = new MappingTableStudyMemberJpaEntity();
        mappingTable.setMember(member);
        mappingTable.setStudy(studyJpaEntity);

        mappingTableStudyMemberRepository.save(mappingTable);
    }
}
