package com.devdive.backend.post.application.port.in;

public interface PostUseCase {

    void createPost(PostCreateRequestApplicationDto dto);

    PostViewApplicationDto viewPost(Long postId);
}
