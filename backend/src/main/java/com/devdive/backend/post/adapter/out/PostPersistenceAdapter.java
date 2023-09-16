package com.devdive.backend.post.adapter.out;

import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.port.out.persistence.LoadPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements LoadPostPort {

    private final PostRepository postRepository;

    @Override
    public void createPost(PostCreateRequestDto dto) {
        PostJpaEntity entity = new PostJpaEntity();
        entity.setContent(dto.getContent());
        entity.setSubtitle(dto.getSubtitle());
        entity.setTitle(dto.getTitle());
        entity.setThumbnail_url(dto.getThumbnailUrl());
        entity.setTag(dto.getTags());
        entity.setStudyId(dto.getStudyId());

        postRepository.save(entity);
    }
}
