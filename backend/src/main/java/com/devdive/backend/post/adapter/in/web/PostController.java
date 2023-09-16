package com.devdive.backend.post.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @PostMapping("/v1/posts")
    ResponseEntity<Void> sendMail(@RequestBody @Valid PostSaveRequestDto dto) {

        System.out.println("--------------");
        System.out.println(dto.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class PostSaveRequestDto {
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
}
