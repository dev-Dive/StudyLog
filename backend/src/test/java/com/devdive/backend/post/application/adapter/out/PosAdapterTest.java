package com.devdive.backend.post.application.adapter.out;

import com.devdive.backend.post.adapter.out.persistence.*;
import com.devdive.backend.post.adapter.out.persistence.repository.MemberPostRepository;
import com.devdive.backend.post.adapter.out.persistence.repository.PostAuthorsRepository;
import com.devdive.backend.post.adapter.out.persistence.repository.PostRepository;
import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DataJpaTest
@ActiveProfiles("test")
public class PosAdapterTest {

    @Autowired
    MemberPostRepository memberPostRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostAuthorsRepository postAuthorsRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("게시글 및 매핑 테이블 생성 테스트")
    public void postMemberMappingTest() {
        // given
        MemberPostJpaEntity member1 = mock(MemberPostJpaEntity.class);
        when(member1.getId()).thenReturn(1L);
        when(memberPostRepository.findById(1L)).thenReturn(Optional.of(member1));
        entityManager.persist(member1);

        PostCreateRequestDto dto1 = new PostCreateRequestDto(
                member1.getId(),
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostCreateRequestDto dto2 = new PostCreateRequestDto(
                member1.getId(),
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );

        PostPersistenceAdapter postPersistenceAdapter =
                new PostPersistenceAdapter(memberPostRepository, postRepository, postAuthorsRepository);

        // when
        postPersistenceAdapter.createPost(dto1);
        postPersistenceAdapter.createPost(dto2);

        // then
        PostJpaEntity savedPost = postRepository.findById(1L).get();
        assertThat(savedPost.getId()).isEqualTo(1L);
        PostJpaEntity savedPost2 = postRepository.findById(2L).get();
        assertThat(savedPost2.getId()).isEqualTo(2L);

        MemberPostJpaEntity savedMember = memberPostRepository.findById(1L).get();
        assertThat(savedMember.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("글 아이디로 조회")
    public void findById() {
        // given
        MemberPostJpaEntity member1 = mock(MemberPostJpaEntity.class);
        when(member1.getId()).thenReturn(1L);
        when(memberPostRepository.findById(eq(1L))).thenReturn(Optional.of(member1));
        entityManager.persist(member1);

        PostCreateRequestDto dto = new PostCreateRequestDto(
                member1.getId(),
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );

        PostPersistenceAdapter adapter =
                new PostPersistenceAdapter(memberPostRepository, postRepository, postAuthorsRepository);
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
