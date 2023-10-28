package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostViewApplicationDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import com.devdive.backend.post.application.port.out.UpdatePostPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {

    private final LoadPostPort loadPostPort;
    private final UpdatePostPort updatePostPort;

    @Transactional
    public void createPost(PostCreateRequestApplicationDto dto) {
        updatePostPort.createPost(dto);
    }

    @Override
    public PostViewApplicationDto viewPost(Long postId) {
        return loadPostPort.viewPost(postId);
    }
}
