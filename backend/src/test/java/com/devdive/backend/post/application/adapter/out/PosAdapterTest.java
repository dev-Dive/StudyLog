package com.devdive.backend.post.application.adapter.out;

import com.devdive.backend.post.adapter.out.*;
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
@Transactional
public class PosAdapterTest {

    @MockBean
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
        MemberPostJpaEntity member2 = mock(MemberPostJpaEntity.class);
        entityManager.persist(member1); // 영속화
        entityManager.persist(member2);
        when(member1.getId()).thenReturn(1L);
        when(member2.getId()).thenReturn(2L);
        when(memberPostRepository.findById(eq(1L))).thenReturn(Optional.of(member1));
        when(memberPostRepository.findById(eq(2L))).thenReturn(Optional.of(member2));

        PostCreateRequestDto dto1 = new PostCreateRequestDto(
                1L,
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostCreateRequestDto dto2 = new PostCreateRequestDto(
                1L,
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostCreateRequestDto dto3 = new PostCreateRequestDto(
                2L,
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
        postPersistenceAdapter.createPost(dto3);

        // then
        List<PostJpaEntity> postJpaData = postRepository.findAll();
        List<PostAuthorsEntity> postAuthorData = postAuthorsRepository.findAll();

        assertThat(postJpaData.size()).isEqualTo(3);
        assertThat(postAuthorData.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("글 아이디로 조회")
    public void findById() {
        // given
        MemberPostJpaEntity member1 = mock(MemberPostJpaEntity.class);
        entityManager.persist(member1);
        when(member1.getId()).thenReturn(1L);
        when(memberPostRepository.findById(eq(1L))).thenReturn(Optional.of(member1));

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
        assertEquals(dto.getThumbnailUrl(), saved.getThumbnail_url());
        assertEquals(dto.getTitle(), saved.getTitle());
        assertEquals(dto.getSubtitle(), saved.getSubtitle());
        assertEquals(dto.getContent(), saved.getContent());
    }

}
