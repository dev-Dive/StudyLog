package com.devdive.backend.post.application.port.in;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;

public interface PostUseCase {

    void createPost(PostCreateRequestDto dto);
}
