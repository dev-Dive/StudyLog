package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostUseCase useCase;

    @PostMapping
    ResponseEntity<Void> createPost(@RequestBody @Valid PostCreateRequestDto dto) {
        useCase.createPost(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    ResponseEntity<Void> viewPost(@PathVariable Long postId) {
        useCase.viewPost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
