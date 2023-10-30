package com.devdive.backend.study.domain;

import java.util.Collections;
import java.util.List;

public class Studies {

    private final List<Study> studies;

    private Studies(List<Study> studies){
        this.studies = studies;
    }

    public List<Study> getStudies() {
        return Collections.unmodifiableList(studies);
    }

    public static Studies from(List<Study> studies){
        return new Studies(studies);
    }
}
