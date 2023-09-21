package com.devdive.backend.post.adapter.out;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity {

    @Id
    @GeneratedValue
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
}
