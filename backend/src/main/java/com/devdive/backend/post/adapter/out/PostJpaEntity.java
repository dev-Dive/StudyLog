package com.devdive.backend.post.adapter.out;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "post")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column
    private Long studyId;

    @Column
    private String thumbnail_url;

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private String content;

    @Column
    @ElementCollection
    private List<String> tag;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostAuthorsEntity> memberMappings = new ArrayList<>();
}
