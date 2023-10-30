package com.devdive.backend.study.application.service;

import com.devdive.backend.study.application.port.out.ReadStudyPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReadStudyServiceTest {

    @Mock
    ReadStudyPort readStudyPort;

    @InjectMocks
    ReadStudyService readStudyService;

    @Test
    @DisplayName("스터디 조회 adapter 요청 테스트")
    public void readStudyTest() {
        // given
        long memberId = 1L;

        // when
        readStudyService.readStudies(memberId);

        // then
        verify(readStudyPort).readStudies(memberId);
    }
}
