package com.devdive.backend.study.application.port.in;

import com.devdive.backend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
public class StudyCreateApplicationDto extends SelfValidating<StudyCreateApplicationDto> {

    @NotNull
    private final Long memberId;

    @NotNull
    private final String name;
    private final String description;

    public StudyCreateApplicationDto(Long memberId, String name, String description) {
        this.memberId = memberId;
        this.name = name;
        this.description = description;

        this.validateSelf();
    }

    public static StudyCreateDtoBuilder builder(){
        return new StudyCreateDtoBuilder();
    }

    public static class StudyCreateDtoBuilder{

        private Long memberId;
        private String name;
        private String description;

        public StudyCreateDtoBuilder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public StudyCreateDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudyCreateDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public StudyCreateApplicationDto build(){
            return new StudyCreateApplicationDto(memberId, name, description);
        }
    }
}
