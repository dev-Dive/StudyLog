package com.devdive.backend.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto {
    private Long id;
    private Long studyId;
    private String thumbnailUrl;
    private String title;
    private String subtitle;
    private String content;
    private List<String> tags;
}
