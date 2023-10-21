package com.devdive.backend.study.adapter.in.web;

import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.core.resolver.AuthenticationPrincipal;
import com.devdive.backend.study.adapter.in.web.payload.StudyCreateRequest;
import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import jakarta.validation.Valid;
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
    ResponseEntity<Void> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid StudyCreateRequest request) {
        StudyCreateApplicationDto applicationDto = StudyCreateApplicationDto.builder()
                .memberId(userDetails.getId())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        useCase.createStudy(applicationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
