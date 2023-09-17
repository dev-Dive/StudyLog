package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.port.out.persistence.LoadPostPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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
}
