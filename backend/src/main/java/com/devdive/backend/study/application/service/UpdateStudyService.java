package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import com.devdive.backend.study.application.port.in.UpdateStudyUseCase;
import com.devdive.backend.study.application.port.out.CommandStudyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateStudyService implements UpdateStudyUseCase {

    private final CommandStudyPort commandStudyPort;

    @Override
    @Transactional
    public void createStudy(StudyCreateApplicationDto dto) {
        commandStudyPort.createStudy(dto);
    }
}
