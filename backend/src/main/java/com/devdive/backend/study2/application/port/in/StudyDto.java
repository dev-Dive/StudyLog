package com.devdive.backend.study2.application.port.in;

import com.devdive.backend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class StudyDto extends SelfValidating<StudyDto> {

    @NotNull
    private final Long memberId;
    @NotNull
    private final String name;
    private final String description;

    @Builder
    public StudyDto(Long memberId, String name, String description) {
        this.memberId = memberId;
        this.name = name;
        this.description = description;

        this.validateSelf();
    }
}
