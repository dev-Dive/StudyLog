package com.devdive.backend.post.application.service;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;
import com.devdive.backend.post.application.port.in.PostUseCase;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService implements PostUseCase {

    @Autowired
    private LoadPostPort loadPostPort;

    public void createPost(PostCreateRequestDto dto) {
        loadPostPort.createPost(dto);
    }

    @Override
    public PostViewDto viewPost(Long postId) {
        return loadPostPort.viewPost(postId);
    }
}
