package com.devdive.backend.post.adapter.in.web.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PostCreateRequest {
    @NotNull
    private Long studyId;

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
