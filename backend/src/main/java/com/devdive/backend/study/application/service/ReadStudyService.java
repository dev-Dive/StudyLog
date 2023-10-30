package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.in.ReadStudyUseCase;
import com.devdive.backend.study.application.port.out.ReadStudyPort;
import com.devdive.backend.study.domain.Studies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadStudyService implements ReadStudyUseCase {

    private final ReadStudyPort readStudyPort;

    @Override
    public Studies readStudies(long memberId) {
        return readStudyPort.readStudies(memberId);
    }
}
