package com.devdive.backend.study2.application.service;

import com.devdive.backend.study2.application.port.in.StudyDto;
import com.devdive.backend.study2.application.port.in.CreateStudyUseCase;
import com.devdive.backend.study2.application.port.out.CreateStudyPort;
import com.devdive.backend.study2.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
class StudyService implements CreateStudyUseCase {

    private final CreateStudyPort createStudyPort;

    @Transactional
    @Override
    public void createStudy(StudyDto dto) {
        createStudyPort.save(Study.init(dto.getMemberId(), dto.getName(), dto.getDescription()));
    }
}
