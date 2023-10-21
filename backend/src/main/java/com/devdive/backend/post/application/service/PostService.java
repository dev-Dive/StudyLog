package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostViewApplicationDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {

    private final LoadPostPort loadPostPort;

    @Transactional
    public void createPost(PostCreateRequestApplicationDto dto) {
        loadPostPort.createPost(dto);
    }

    @Override
    public PostViewApplicationDto viewPost(Long postId) {
        return loadPostPort.viewPost(postId);
    }
}
