package com.devdive.backend.study.adapter.in.web.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudyCreateRequest {

    @NotNull
    private String name;

    private String description;
}
