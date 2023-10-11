package com.devdive.backend.post.adapter.out.persistence;

import com.devdive.backend.post.adapter.out.persistence.repository.MemberPostRepository;
import com.devdive.backend.post.adapter.out.persistence.repository.PostAuthorsRepository;
import com.devdive.backend.post.adapter.out.persistence.repository.PostRepository;
import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import com.devdive.backend.post.application.dto.PostViewDto;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements LoadPostPort {

    private final MemberPostRepository memberPostRepository;

    private final PostRepository postRepository;

    private final PostAuthorsRepository postAuthorsRepository;

    @Override
    public void createPost(PostCreateRequestDto dto) {

        MemberPostJpaEntity member = memberPostRepository.findById(dto.getMemberId()).orElseThrow(IllegalArgumentException::new);

        PostJpaEntity post = new PostJpaEntity(
                dto.getStudyId(),
                dto.getThumbnailUrl(),
                dto.getTitle(),
                dto.getSubtitle(),
                dto.getContent(),
                dto.getTags()
        );

        postRepository.save(post);

        PostAuthorsEntity mappingTable = new PostAuthorsEntity();
        mappingTable.setMember(member);
        mappingTable.setPost(post);

        postAuthorsRepository.save(mappingTable);
    }

    @Override
    public PostViewDto viewPost(Long postId) {
        PostJpaEntity entity = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);

        return new PostViewDto(
                entity.getId(),
                entity.getStudyId(),
                entity.getThumbnailUrl(),
                entity.getTitle(),
                entity.getSubtitle(),
                entity.getContent(),
                entity.getTag()
        );
    }

    @Override
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }
}
