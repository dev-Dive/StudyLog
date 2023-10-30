package com.devdive.backend.study.application.port.in;

import com.devdive.backend.study.domain.Studies;

public interface ReadStudyUseCase {

    Studies readStudies(long memberId);

}
