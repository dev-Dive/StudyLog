package com.devdive.backend.study.application.port.out;

import com.devdive.backend.study.domain.Studies;

public interface ReadStudyPort{

    Studies readStudies(long memberId);
}
