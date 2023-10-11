package com.devdive.backend.post.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

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
    private List<PostAuthorsEntity> postAuthors = new ArrayList<>();

    public PostJpaEntity(Long studyId,
                         String thumbnailUrl,
                         String title,
                         String subtitle,
                         String content,
                         List<String> tag) {
        this.studyId = studyId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.tag = tag;
    }
}
