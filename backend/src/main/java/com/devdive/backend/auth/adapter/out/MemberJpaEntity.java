package com.devdive.backend.auth.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MemberJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "mail")
    private String mail;

    @Builder
    public MemberJpaEntity(String mail) {
        this.mail = mail;
    }
}
