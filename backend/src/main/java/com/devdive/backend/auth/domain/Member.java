package com.devdive.backend.auth.domain;

import lombok.Getter;

@Getter
public class Member {

    private String mail;

    public Member(String mail) {
        this.mail = mail;
    }
}
