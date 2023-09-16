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
    Long id;

    @Column
    Long studyId;

    @Column
    String thumbnail_url;

    @Column
    String title;

    @Column
    String subtitle;

    @Column
    String content;

    @Column
    @ElementCollection
    List<String> tag;
}
