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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class PostCreateTest {

    @Autowired
    PostRepository repository;

    @Test
    @DisplayName("글 생성")
    public void creatPost(){
        PostCreateRequestDto dto = new PostCreateRequestDto(
                1L,
                "http://",
                "title",
                "sub",
                "content",
                List.of("tag1", "tag2")
        );
        PostPersistenceAdapter adapter = new PostPersistenceAdapter(repository);
        adapter.createPost(dto);

        PostJpaEntity saved = repository.findById(1L).get();

        assertEquals(1L, saved.getStudyId());
        assertEquals("http://", saved.getThumbnail_url());
        assertEquals("title", saved.getTitle());
        assertEquals("sub", saved.getSubtitle());
        assertEquals("content", saved.getContent());
    }
}
