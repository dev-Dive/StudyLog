package com.devdive.backend.persistance.entities.compositekey;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.StudyJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StudyMembersId implements Serializable {

    private MemberJpaEntity member;
    private StudyJpaEntity study;
}
