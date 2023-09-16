package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PostMapping("/v1/posts")
    ResponseEntity<Void> sendMail(@RequestBody @Valid PostCreateRequestDto dto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
