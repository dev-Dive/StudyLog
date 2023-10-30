package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import com.devdive.backend.study.application.port.out.CommandStudyPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateStudyServiceTest {

    @Mock
    CommandStudyPort commandStudyPort;

    @InjectMocks
    UpdateStudyService updateStudyService;


    @Test
    @DisplayName("스터디 생성 adapter요청 테스트")
    public void createStudyTest(){
        // given
        StudyCreateApplicationDto dto = new StudyCreateApplicationDto(1L, "name1", "desc1");
        doNothing().when(commandStudyPort).createStudy(dto);

        // when
        updateStudyService.createStudy(dto);

        // then
        verify(commandStudyPort).createStudy(dto);
    }

}
