package com.devdive.backend.post.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {

    private Long memberId;

    @NotNull
    private Long studyId;

    @Nullable
    private String thumbnailUrl;

    @NotNull
    private String title;

    @NotNull
    private String subtitle;

    @NotNull
    private String content;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @NotNull
    private List<String> tags;
}
