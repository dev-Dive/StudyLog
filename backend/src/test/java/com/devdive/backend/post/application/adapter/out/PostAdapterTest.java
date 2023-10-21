package com.devdive.backend.post.application.adapter.out;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.PostJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.PostMemberRepository;
import com.devdive.backend.post.adapter.out.persistence.*;
import com.devdive.backend.persistance.repository.PostRepository;
import com.devdive.backend.post.application.port.in.PostCreateRequestApplicationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class PostAdapterTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMemberRepository postMemberRepository;

    @Test
    @DisplayName("게시글 및 매핑 테이블 생성 테스트")
    public void postMemberMappingTest() {
        // given
        MemberJpaEntity member1 = new MemberJpaEntity();
        memberRepository.save(member1);

        PostCreateRequestApplicationDto dto1 = PostCreateRequestApplicationDto.builder()
                .memberId(member1.getId())
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();

        PostCreateRequestApplicationDto dto2 = PostCreateRequestApplicationDto.builder()
                .memberId(member1.getId())
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();

        PostPersistenceAdapter postPersistenceAdapter =
                new PostPersistenceAdapter(memberRepository , postRepository, postMemberRepository);

        // when
        postPersistenceAdapter.createPost(dto1);
        postPersistenceAdapter.createPost(dto2);

        // then
        assertThat(postRepository.count()).isEqualTo(2);
        assertThat(postMemberRepository.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("글 아이디로 조회")
    public void findById() {
        // given
        MemberJpaEntity member1 = new MemberJpaEntity();
        memberRepository.save(member1);

        PostCreateRequestApplicationDto dto = PostCreateRequestApplicationDto.builder()
                .memberId(member1.getId())
                .studyId(10L)
                .thumbnailUrl("http://")
                .title("title")
                .subtitle("sub")
                .content("content")
                .tags(List.of("tag1", "tag2"))
                .build();

        PostPersistenceAdapter adapter =
                new PostPersistenceAdapter(memberRepository, postRepository, postMemberRepository);
        adapter.createPost(dto);

        // when
        PostJpaEntity saved = postRepository.findById(1L).get();

        // then
        assertEquals(1L, saved.getId());
        assertEquals(dto.getStudyId(), saved.getStudyId());
        assertEquals(dto.getThumbnailUrl(), saved.getThumbnailUrl());
        assertEquals(dto.getTitle(), saved.getTitle());
        assertEquals(dto.getSubtitle(), saved.getSubtitle());
        assertEquals(dto.getContent(), saved.getContent());
    }
}
