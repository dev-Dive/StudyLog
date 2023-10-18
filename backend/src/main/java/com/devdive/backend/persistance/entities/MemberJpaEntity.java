package com.devdive.backend.persistance.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
public class MemberJpaEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    @Column
    String mail;

    @Column
    String profileUrl;

    @OneToMany(mappedBy = "member")
    private List<StudyMemberJpaEntity> studyMappings = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PostMemberJpaEntity> postMappings = new ArrayList<>();

    @Builder
    public MemberJpaEntity(String mail) {
        this.mail = mail;
    }

    public MemberJpaEntity(Long id, String name, String mail, String profileUrl) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.profileUrl = profileUrl;
    }
}
