package com.devdive.backend.study2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class People {

    private final List<Person> people;

    public People(List<Person> people) {
        this.people = people;
    }

    private People(Person owner) {
        this.people = new ArrayList<>(List.of(owner));
    }

    public static People init(Long id) {
        return new People(Person.owner(id));
    }

    public List<Person> getList(){
        return Collections.unmodifiableList(people);
    }
}
