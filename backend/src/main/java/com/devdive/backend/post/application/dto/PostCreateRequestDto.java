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
    @NotNull
    Long studyId;
    @Nullable
    String thumbnailUrl;
    @NotNull
    String title;
    @NotNull
    String subtitle;
    @NotNull
    String content;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @NotNull
    List<String> tags;
}
