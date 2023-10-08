package com.devdive.backend.post.application.port.out;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;

import java.util.Optional;

public interface LoadPostPort {

    void createPost(PostCreateRequestDto dto);

    PostViewDto viewPost(Long postId);

    void deletePost(long postId);
}
