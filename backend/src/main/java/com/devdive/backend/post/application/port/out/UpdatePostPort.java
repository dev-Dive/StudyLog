package com.devdive.backend.post.application.port.out;

import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;

public interface UpdatePostPort {

    void createPost(PostCreateRequestApplicationDto dto);

    void deletePost(long postId);
}
