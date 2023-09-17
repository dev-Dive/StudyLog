package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostUseCase useCase;

    @PostMapping("/v1/posts")
    ResponseEntity<Void> sendMail(@RequestBody @Valid PostCreateRequestDto dto) {
        useCase.createPost(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
