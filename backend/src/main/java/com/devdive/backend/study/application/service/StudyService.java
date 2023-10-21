package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import com.devdive.backend.study.application.port.out.LoadStudyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyService implements StudyUseCase {

    private final LoadStudyPort loadStudyPort;

    @Override
    public void createStudy(StudyCreateApplicationDto dto) {
        loadStudyPort.createStudy(dto);
    }
}
