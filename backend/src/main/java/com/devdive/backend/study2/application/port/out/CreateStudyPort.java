package com.devdive.backend.study2.application.port.out;

import com.devdive.backend.study2.domain.Study;

public interface CreateStudyPort {

    void save(Study study);
}
