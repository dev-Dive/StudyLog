package com.devdive.backend.study.adapter.out.persistence.compositekey;

import com.devdive.backend.study.adapter.out.persistence.StudyJpaEntity;
import com.devdive.backend.study.adapter.out.persistence.MemberStudyJpaEntity;
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
