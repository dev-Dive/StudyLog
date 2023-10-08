package com.devdive.backend.study.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyCreateDto {

    private Long memberId;

    @NotNull
    private String name;

    private String description;
}
