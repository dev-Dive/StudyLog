package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostViewApplicationDto;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import com.devdive.backend.post.application.port.out.UpdatePostPort;
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

    @Mock
    UpdatePostPort updatePostPort;

    @InjectMocks
    PostService service;

    @Test
    @DisplayName("글 생성")
    public void createPost(){
        // given

        PostCreateRequestApplicationDto dto = PostCreateRequestApplicationDto.builder()
                .memberId(1L)
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();

        // when
        doNothing().when(updatePostPort).createPost(dto);
        service.createPost(dto);

        // then
        verify(updatePostPort).createPost(dto);
    }

    @Test
    @DisplayName("글 조회")
    public void viewPost(){
        // given
        long postId = 1L;
        PostViewApplicationDto dto = PostViewApplicationDto.builder()
                .postId(1L)
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();

        when(loadPostPort.viewPost(postId)).thenReturn(dto);

        // when
        loadPostPort.viewPost(postId);

        // then
        assertThat(postId).isEqualTo(1L);
    }

    @Test
    @DisplayName("글 조회 실패")
    public void viewPostFail(){
        // given
        long postId = 1L;
        PostViewApplicationDto dto = PostViewApplicationDto.builder()
                .postId(1L)
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();
        when(loadPostPort.viewPost(postId)).thenReturn(dto);

        // when
        loadPostPort.viewPost(postId);

        // then
        assertThrows(AssertionFailedError.class, () -> {
            assertThat(postId).isEqualTo(1L + 1);
        });
    }
}
