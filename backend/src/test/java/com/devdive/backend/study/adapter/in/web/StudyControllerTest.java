package com.devdive.backend.study.adapter.in.web;

import com.devdive.backend.study.application.port.in.StudyCreateDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudyController.class)
class StudyControllerTest {

    @MockBean
    StudyUseCase studyUseCase;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("스터디 생성 컨트롤러 테스트")
    public void createStudy() throws Exception {
        // given
        StudyCreateDto dto = new StudyCreateDto(1L, "name1", "desc1");
        doNothing().when(studyUseCase).createStudy(dto);
        ObjectMapper mapper = new ObjectMapper();

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/studies")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());

    }
}
