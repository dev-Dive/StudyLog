package com.devdive.backend.study.domain;

import com.devdive.backend.persistance.entities.StudyJpaEntity;

import java.util.ArrayList;
import java.util.List;

public class Study {
    Long id;
    String name;
    String description;
    String profileUrl;

    private Study(Long id, String name, String description, String profileUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profileUrl = profileUrl;
    }

    public static Study of(Long id, String name, String description, String profileUrl){
        return new Study(id, name, description, profileUrl);
    }
}
