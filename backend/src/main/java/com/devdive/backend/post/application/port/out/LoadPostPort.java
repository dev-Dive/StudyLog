package com.devdive.backend.post.application.port.out;

import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostViewApplicationDto;

public interface LoadPostPort {

    void createPost(PostCreateRequestApplicationDto dto);

    PostViewApplicationDto viewPost(Long postId);

    void deletePost(long postId);
}
