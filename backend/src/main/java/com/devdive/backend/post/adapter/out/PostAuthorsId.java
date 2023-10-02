package com.devdive.backend.post.adapter.out;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PostAuthorsId implements Serializable {

    private MemberPostJpaEntity member;
    private PostJpaEntity post;
}
