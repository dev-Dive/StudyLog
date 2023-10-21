package com.devdive.backend.persistance.entities;

import com.devdive.backend.persistance.entities.compositekey.PostMembersId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_members")
@Getter
@Setter
@NoArgsConstructor
@IdClass(PostMembersId.class)
public class PostMemberJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;


    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;
}
