package com.devdive.backend.study2.application.service;

import com.devdive.backend.study2.application.port.in.StudyDto;
import com.devdive.backend.study2.application.port.out.CreateStudyPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    CreateStudyPort createStudyPort;

    @InjectMocks
    StudyService updateStudyService;


    @Test
    @DisplayName("스터디 생성 adapter요청 테스트")
    public void createStudyTest(){
        // given
        StudyDto dto = new StudyDto(1L, "name1", "desc1");

        doNothing().when(createStudyPort).save(any());

        // when
        updateStudyService.createStudy(dto);

        // then
        verify(createStudyPort).save(any());
    }

}
