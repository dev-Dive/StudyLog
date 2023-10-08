package com.devdive.backend.study.application.port.out;

import com.devdive.backend.study.application.dto.StudyCreateDto;

public interface LoadStudyPort {

    void createStudy(StudyCreateDto dto);
}
