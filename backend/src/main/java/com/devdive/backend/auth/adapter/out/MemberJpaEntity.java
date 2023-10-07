package com.devdive.backend.auth.adapter.out;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mail")
    private String mail;

    @Builder
    public MemberJpaEntity(String mail) {
        this.mail = mail;
    }
}
