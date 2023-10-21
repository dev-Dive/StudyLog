package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.adapter.in.web.payload.PostCreateRequest;
import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.core.resolver.AuthenticationPrincipal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostUseCase useCase;

    @PostMapping
    ResponseEntity<Void> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid PostCreateRequest request) {
        PostCreateRequestApplicationDto applicationDto = PostCreateRequestApplicationDto.builder()
                .memberId(userDetails.getId())
                .studyId(request.getStudyId())
                .thumbnailUrl(request.getThumbnailUrl())
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .content(request.getContent())
                .tags(request.getTags())
                .build();

        useCase.createPost(applicationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    ResponseEntity<Void> viewPost(@PathVariable Long postId) {
        useCase.viewPost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
