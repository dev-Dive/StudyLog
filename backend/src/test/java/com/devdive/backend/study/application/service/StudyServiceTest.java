package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.in.StudyCreateDto;
import com.devdive.backend.study.application.port.out.LoadStudyPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    LoadStudyPort loadStudyPort;

    @InjectMocks
    StudyService studyService;


    @Test
    @DisplayName("스터디 생성 adapter에 요청")
    public void createStudy(){
        // given
        StudyCreateDto dto = new StudyCreateDto(1L, "name1", "desc1");
        doNothing().when(loadStudyPort).createStudy(dto);

        // when
        studyService.createStudy(dto);

        // then
        verify(loadStudyPort).createStudy(dto);
    }

}
