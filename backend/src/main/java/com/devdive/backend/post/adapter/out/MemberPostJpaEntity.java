package com.devdive.backend.post.adapter.out;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "member_post")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPostJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_post_id")
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
