package com.devdive.backend.study.adapter.in.web;

import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.core.securitycontext.SecurityContext;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import com.devdive.backend.study.application.port.in.ReadStudyUseCase;
import com.devdive.backend.study.application.port.in.UpdateStudyUseCase;
import com.devdive.backend.study.domain.Studies;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudyController.class)
class StudyControllerTest {

    @MockBean
    ReadStudyUseCase readStudyUseCase;

    @MockBean
    UpdateStudyUseCase updatestudyUseCase;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("스터디 생성 컨트롤러 테스트")
    public void createStudy() throws Exception {
        // given
        String mail = "rhwlgns@gmail.com";

        SecurityContext securityContext =SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new Authentication() {

            private User user = new User(mail, 1L);

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public String getAuthorities() {
                return null;
            }
        });
        SecurityContextHolder.addContext(securityContext);

        JSONObject resquestJson = new JSONObject();
        resquestJson.put("name", mail);
        resquestJson.put("description", mail);

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/studies")
                .content(resquestJson.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("스터디 조회 컨트롤러 테스트")
    public void readStudies() throws Exception {
        // given
        String mail = "rhwlgns@gmail.com";

        SecurityContext securityContext =SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new Authentication() {

            private User user = new User(mail, 1L);

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public String getAuthorities() {
                return null;
            }
        });
        SecurityContextHolder.addContext(securityContext);

        when(readStudyUseCase.readStudies(eq(1L))).thenReturn(Studies.from(List.of()));

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/studies")
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("서버로 파일 전송 테스트")
    // Todo 컨트롤러 수정해서 성공시켜야하는 테스트
    public void fileUploadTest() throws Exception {
        // given
        String mail = "rhwlgns@gmail.com";

        SecurityContext securityContext =SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new Authentication() {

            private User user = new User(mail, 1L);

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public String getAuthorities() {
                return null;
            }
        });
        SecurityContextHolder.addContext(securityContext);

        String url = "src\\test\\resources\\img\\studyImage.jpg";
        byte[] fileContent = Files.readAllBytes(Paths.get(url));
        MockMultipartFile studyImage = new MockMultipartFile(
                "studyImage",
                "studyImage.jpg",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileContent);

        JSONObject resquestJson = new JSONObject();
        resquestJson.put("name", mail);
        resquestJson.put("description", mail);

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .multipart("/api/v1/studies")
                .file("studyImage", studyImage.getBytes())
                .param("name", "n")
                .param("description", "d")
                .contentType(MediaType.MULTIPART_FORM_DATA);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
