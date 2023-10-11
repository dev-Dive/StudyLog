package com.devdive.backend.post.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPostJpaEntity {

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
    private List<PostAuthorsEntity> postMappings = new ArrayList<>();
}
