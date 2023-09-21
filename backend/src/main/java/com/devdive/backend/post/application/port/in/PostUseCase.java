package com.devdive.backend.post.application.port.in;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;

public interface PostUseCase {

    void createPost(PostCreateRequestDto dto);

    PostViewDto viewPost(Long postId);
}
