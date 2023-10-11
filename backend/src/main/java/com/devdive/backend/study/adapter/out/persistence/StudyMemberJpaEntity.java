package com.devdive.backend.study.adapter.out.persistence;

import com.devdive.backend.study.adapter.out.persistence.compositekey.StudyMembersId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "study_members")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(StudyMembersId.class)
public class StudyMemberJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_study_id")
    private MemberStudyJpaEntity member;


    @Id
    @ManyToOne
    @JoinColumn(name = "study_id")
    private StudyJpaEntity study;

}
