package com.devdive.backend.study.application.port.out;

import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;

public interface CommandStudyPort {

    void createStudy(StudyCreateApplicationDto dto);
}
