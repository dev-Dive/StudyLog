package com.devdive.backend.persistance.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studies")
@Getter
@Setter
@NoArgsConstructor
public class StudyJpaEntity {

    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String profileUrl;

    @OneToMany(mappedBy = "study")
    private List<StudyMemberJpaEntity> memberMappings = new ArrayList<>();
}
