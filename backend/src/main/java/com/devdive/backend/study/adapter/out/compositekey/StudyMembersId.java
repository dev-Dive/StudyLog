package com.devdive.backend.study.adapter.out.compositekey;

import com.devdive.backend.study.adapter.out.StudyJpaEntity;
import com.devdive.backend.study.adapter.out.MemberStudyJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StudyMembersId implements Serializable {

    private MemberStudyJpaEntity member;
    private StudyJpaEntity study;
}
