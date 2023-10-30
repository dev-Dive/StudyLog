package com.devdive.backend.study.adapter.out.persistence;

import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.study.application.port.out.ReadStudyPort;
import com.devdive.backend.study.domain.Studies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadStudyPersistenceAdapter implements ReadStudyPort {

    private final MemberRepository memberStudyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyJpaEntityMapper studyJpaEntityMapper;

    @Override
    public Studies readStudies(long memberId) {
        memberStudyRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        return studyJpaEntityMapper.studyJpaEntitiesToStudies(studyMemberRepository.readStudies(memberId));
    }
}
