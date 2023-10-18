package com.devdive.backend.study.application.port.out;

import com.devdive.backend.study.application.port.in.StudyCreateDto;

public interface LoadStudyPort {

    void createStudy(StudyCreateDto dto);
}
