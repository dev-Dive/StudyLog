package com.devdive.backend.study2.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Role {
    OWNER("owner"),
    MEMBER("member");

    private final String name;

    Role(String name){
        this.name = name;
    }

    public static Role findByName(String name) {
        return Arrays.stream(Role.values())
                .filter(r -> r.name.equals(name.toLowerCase()))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
