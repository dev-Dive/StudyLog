package com.devdive.backend.study.adapter.out.persistence;

import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.study.domain.Studies;
import com.devdive.backend.study.domain.Study;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudyJpaEntityMapper {

    public Studies studyJpaEntitiesToStudies(List<StudyJpaEntity> studyJpaEntities) {
        List<Study> studies = new ArrayList<>();

        for (StudyJpaEntity se : studyJpaEntities){
            studies.add(Study.of(se.getId(), se.getName(), se.getDescription(), se.getProfileUrl()));
        }

        return Studies.from(studies);
    }
}
