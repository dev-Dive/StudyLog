package com.devdive.backend.study.adapter.in.web;

import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.core.resolver.AuthenticationPrincipal;
import com.devdive.backend.study.application.port.in.StudyCreateDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyController {

    private final StudyUseCase useCase;

    @PostMapping
    ResponseEntity<Void> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid StudyRequestDto dto) {
        System.out.println("------");
        Thread thread=Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println(userDetails.getId());
        System.out.println("------");


        StudyCreateDto createDto = new StudyCreateDto(userDetails.getId(), dto.getName(), dto.getDescription());
        useCase.createStudy(createDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Data
    static class StudyRequestDto {

        @NotNull
        private String name;

        private String description;
    }

}
