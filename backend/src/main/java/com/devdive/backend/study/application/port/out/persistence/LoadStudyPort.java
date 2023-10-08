package com.devdive.backend.study.application.port.out.persistence;

import com.devdive.backend.study.application.dto.StudyCreateDto;

public interface LoadStudyPort {

    void createStudy(StudyCreateDto dto);
}
