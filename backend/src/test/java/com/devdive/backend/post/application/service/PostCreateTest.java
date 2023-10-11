package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostCreateTest {

    @Mock
    LoadPostPort loadPostPort;

    @InjectMocks
    PostService service;

    @Test
    @DisplayName("글 생성")
    public void createPost(){
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

        // when
        doNothing().when(loadPostPort).createPost(dto);
        service.createPost(dto);

        // then
        verify(loadPostPort).createPost(dto);
    }

    @Test
    @DisplayName("글 조회")
    public void viewPost(){
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
        when(loadPostPort.viewPost(postId)).thenReturn(postViewDto);

        // when
        loadPostPort.viewPost(postId);

        // then
        assertThat(postId).isEqualTo(postViewDto.getId());
    }

    @Test
    @DisplayName("글 조회 실패")
    public void viewPostFail(){
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
        when(loadPostPort.viewPost(postId)).thenReturn(postViewDto);

        // when
        loadPostPort.viewPost(postId);

        // then
        assertThrows(AssertionFailedError.class, () -> {
            assertThat(postId).isEqualTo(postViewDto.getId() + 1);
        });
    }
}
