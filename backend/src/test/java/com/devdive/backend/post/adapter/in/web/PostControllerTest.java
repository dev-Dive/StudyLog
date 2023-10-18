package com.devdive.backend.post.adapter.in.web;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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
        PostCreateRequestDto dto = new PostCreateRequestDto(
                1L,
                1L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        ObjectMapper mapper = new ObjectMapper();
        doNothing().when(useCase).createPost(dto);

        // when
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/posts")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("글 조회 테스트")
    public void viewPost() throws Exception {
        // given
        long postId = 1L;

        PostViewDto postViewDto = new PostViewDto(
                postId,
                1L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2"));
        when(useCase.viewPost(eq(postId))).thenReturn(postViewDto);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/posts/" + postId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
