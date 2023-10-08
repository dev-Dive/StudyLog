package com.devdive.backend.study.application.port.in;

import com.devdive.backend.study.application.dto.StudyCreateDto;

public interface StudyUseCase {

    void createStudy(StudyCreateDto dto);

}
