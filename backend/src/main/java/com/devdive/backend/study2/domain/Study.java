package com.devdive.backend.study2.domain;

import lombok.Getter;
import lombok.Value;


public class Study {

    @Getter
    private StudyId id;
    @Getter
    private StudyData studyData;
    @Getter
    private People people;

    public Study(StudyId id, StudyData studyData, People people) {
        this.id = id;
        this.studyData = studyData;
        this.people = people;
    }

    public Study(People people, StudyData studyData) {
        this.people = people;
        this.studyData = studyData;
    }

    public static Study init(Long ownerId, String name, String description) {
        return new Study(People.init(ownerId), new StudyData(name, description));
    }

    public void changeId(Long id) {
        this.id = new StudyId(id);
    }

    @Value
    public static class StudyId {
        Long value;
    }

    @Value
    public static class StudyData {
        String name;
        String description;
    }
}
