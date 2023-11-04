//package com.devdive.backend.study.adapter.in.web;
//
//import com.devdive.backend.security.authentication.domain.UserDetails;
//import com.devdive.backend.security.core.resolver.AuthenticationPrincipal;
//import com.devdive.backend.study.adapter.in.web.payload.StudyCreateRequest;
//import com.devdive.backend.study.application.port.in.ReadStudyUseCase;
//import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
//import com.devdive.backend.study.application.port.in.UpdateStudyUseCase;
//import com.devdive.backend.study.domain.Studies;
//import com.devdive.backend.study.domain.Study;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/studies")
//@RequiredArgsConstructor
//public class StudyController {
//
//    private final ReadStudyUseCase readStudyUseCase;
//    private final UpdateStudyUseCase updateStudyUseCase;
//
//    @GetMapping
//    ResponseEntity<List<Study>> readStudies(@AuthenticationPrincipal UserDetails userDetails) {
//        Studies studies = readStudyUseCase.readStudies(userDetails.getId());
//        return new ResponseEntity<>(studies.getStudies(), HttpStatus.OK);
//    }
//
//    @PostMapping
//    ResponseEntity<Void> createStudy(@AuthenticationPrincipal UserDetails userDetails,@RequestBody @Valid StudyCreateRequest request) {
//        StudyCreateApplicationDto applicationDto = StudyCreateApplicationDto.builder()
//                .memberId(userDetails.getId())
//                .name(request.getName())
//                .description(request.getDescription())
//                .studyImage(request.getStudyImage())
//                .build();
//
//        updateStudyUseCase.createStudy(applicationDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
