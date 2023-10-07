package com.devdive.backend.post.adapter.out;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(PostAuthorsId.class)
public class PostAuthorsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberPostJpaEntity member;


    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;
}
