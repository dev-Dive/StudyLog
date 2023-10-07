package com.devdive.backend.post.adapter.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PostAuthorsId implements Serializable {

    private MemberPostJpaEntity member;
    private PostJpaEntity post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostAuthorsId that = (PostAuthorsId) o;
        return Objects.equals(member, that.member) && Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, post);
    }
}
