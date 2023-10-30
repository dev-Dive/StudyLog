package com.devdive.backend.study.application.port.in;

import com.devdive.backend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class StudyCreateApplicationDto extends SelfValidating<StudyCreateApplicationDto> {

    @NotNull
    private final Long memberId;

    @NotNull
    private final String name;
    private final String description;
    private final MultipartFile studyImage;

    public StudyCreateApplicationDto(Long memberId, String name, String description, MultipartFile studyImage) {
        this.memberId = memberId;
        this.name = name;
        this.description = description;
        this.studyImage = studyImage;

        this.validateSelf();
    }

    public static StudyCreateDtoBuilder builder(){
        return new StudyCreateDtoBuilder();
    }

    public static class StudyCreateDtoBuilder{

        private Long memberId;
        private String name;
        private String description;
        private MultipartFile studyImage;

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

        public StudyCreateDtoBuilder studyImage(MultipartFile studyImage) {
            this.studyImage = studyImage;
            return this;
        }

        public StudyCreateApplicationDto build(){
            return new StudyCreateApplicationDto(memberId, name, description, studyImage);
        }
    }
}
