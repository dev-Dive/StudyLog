package com.devdive.backend.post.adapter.out.persistence;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.PostJpaEntity;
import com.devdive.backend.persistance.entities.PostMemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.PostMemberRepository;
import com.devdive.backend.persistance.repository.PostRepository;
import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import com.devdive.backend.post.application.port.in.PostViewApplicationDto;
import com.devdive.backend.post.application.port.out.LoadPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements LoadPostPort {

    private final MemberRepository memberPostRepository;

    private final PostRepository postRepository;

    private final PostMemberRepository postMemberRepository;

    @Override
    public void createPost(PostCreateRequestApplicationDto dto) {

        MemberJpaEntity member = memberPostRepository.findById(dto.getMemberId()).orElseThrow(IllegalArgumentException::new);

        PostJpaEntity post = new PostJpaEntity(
                dto.getStudyId(),
                dto.getThumbnailUrl(),
                dto.getTitle(),
                dto.getSubtitle(),
                dto.getContent(),
                dto.getTags()
        );

        postRepository.save(post);

        PostMemberJpaEntity mappingTable = new PostMemberJpaEntity();
        mappingTable.setMember(member);
        mappingTable.setPost(post);

        postMemberRepository.save(mappingTable);
    }

    @Override
    public PostViewApplicationDto viewPost(Long postId) {
        PostJpaEntity entity = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);


        PostViewApplicationDto applicationViwDto =
                PostViewApplicationDto.builder()
                .postId(entity.getId())
                .studyId(entity.getStudyId())
                .thumbnailUrl(entity.getThumbnailUrl())
                .title(entity.getTitle())
                .subtitle(entity.getSubtitle())
                .content(entity.getContent())
                .tags(entity.getTag())
                .build();

        return applicationViwDto;
    }

    @Override
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }
}
