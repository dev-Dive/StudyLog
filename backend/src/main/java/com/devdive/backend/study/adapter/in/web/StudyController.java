package com.devdive.backend.study.adapter.in.web;

import com.devdive.backend.study.application.dto.StudyCreateDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyUseCase useCase;

    @PostMapping("v1/study/create")
    ResponseEntity<Void> createPost(@RequestBody @Valid StudyCreateDto dto) {
        useCase.createStudy(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
