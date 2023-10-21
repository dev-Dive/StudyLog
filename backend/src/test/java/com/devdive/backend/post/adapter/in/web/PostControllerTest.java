package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.application.port.in.PostUseCase;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.core.securitycontext.SecurityContext;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    @MockBean
    PostUseCase useCase;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("게시글 생성 테스트")
    public void createPostTest() throws Exception {
        // given
        String mail = "rhwlgns@gmail.com";

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
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

        JSONObject requestJson = new JSONObject();
        requestJson.put("studyId", 1L);
        requestJson.put("thumbnailUrl", "http://");
        requestJson.put("title", "t");
        requestJson.put("subtitle", "s");
        requestJson.put("content", "c");
        requestJson.put("tags", List.of("tag1", "tag2"));

        doNothing().when(useCase).createPost(any());

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/posts")
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("글 조회 테스트")
//    public void viewPost() throws Exception {
//        // given
//        long postId = 1L;
//
//        PostViewDto postViewDto = new PostViewDto(
//                postId,
//                1L,
//                "http://",
//                "title",
//                "sub",
//                "content",
//                List.of("tag1", "tag2"));
//        when(useCase.viewPost(eq(postId))).thenReturn(postViewDto);
//
//        RequestBuilder request = MockMvcRequestBuilders
//                .get("/api/v1/posts/" + postId)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request)
//                .andExpect(status().isOk());
//    }
}
