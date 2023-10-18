package com.devdive.backend.persistance.entities;

import com.devdive.backend.persistance.entities.compositekey.StudyMembersId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "study_members")
@Getter
@Setter
@NoArgsConstructor
@IdClass(StudyMembersId.class)
public class StudyMemberJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;


    @Id
    @ManyToOne
    @JoinColumn(name = "study_id")
    private StudyJpaEntity study;

}
