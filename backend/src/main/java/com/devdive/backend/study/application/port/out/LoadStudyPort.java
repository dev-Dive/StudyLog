package com.devdive.backend.study.application.port.out;

import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;

public interface LoadStudyPort {

    void createStudy(StudyCreateApplicationDto dto);
}
