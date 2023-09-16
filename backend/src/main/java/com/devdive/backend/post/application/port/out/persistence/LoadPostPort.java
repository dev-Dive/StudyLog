package com.devdive.backend.post.application.port.out.persistence;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;

public interface LoadPostPort {

    void createPost(PostCreateRequestDto dto);
}
