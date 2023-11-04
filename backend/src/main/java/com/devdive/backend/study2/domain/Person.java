package com.devdive.backend.study2.domain;

import lombok.Getter;
import lombok.Value;

public class Person {

    @Getter private final PersonId id;
    @Getter private final Role role;

    public Person(PersonId id, Role role) {
        this.id = id;
        this.role = role;
    }

    public static Person owner(Long id) {
        return new Person(new PersonId(id), Role.OWNER);
    }

    @Value
    public static class PersonId {
        Long value;
    }
}
