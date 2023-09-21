package com.devdive.backend.post.application.adapter.out;

import com.devdive.backend.post.adapter.out.PostJpaEntity;
import com.devdive.backend.post.adapter.out.PostPersistenceAdapter;
import com.devdive.backend.post.adapter.out.PostRepository;
import com.devdive.backend.post.application.dto.PostCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PostAdapterTest {

    @Autowired
    PostRepository repository;

    @Test
    @DisplayName("글 생성")
    public void creatPost() {
        // given
        PostCreateRequestDto dto = new PostCreateRequestDto(
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostPersistenceAdapter adapter = new PostPersistenceAdapter(repository);
        adapter.createPost(dto);


        List<PostJpaEntity> d = repository.findAll();
        for(int i=0; i<d.size(); i++){
            System.out.println(d.get(i).getId());
        }
        // when
        PostJpaEntity saved = repository.findAll().get(0);

        // then
        assertNotNull(saved);
    }

    @Test
    @DisplayName("글 아이디로 조회")
    public void findById() {
        PostCreateRequestDto dto = new PostCreateRequestDto(
                10L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostPersistenceAdapter adapter = new PostPersistenceAdapter(repository);
        adapter.createPost(dto);

        PostJpaEntity saved = repository.findById(1L).get();

        assertEquals(1L, saved.getId());
        assertEquals(dto.getStudyId(), saved.getStudyId());
        assertEquals(dto.getThumbnailUrl(), saved.getThumbnail_url());
        assertEquals(dto.getTitle(), saved.getTitle());
        assertEquals(dto.getSubtitle(), saved.getSubtitle());
        assertEquals(dto.getContent(), saved.getContent());
    }
}
