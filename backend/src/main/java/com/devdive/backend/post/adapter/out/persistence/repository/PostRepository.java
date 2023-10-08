package com.devdive.backend.post.adapter.out.persistence.repository;

import com.devdive.backend.post.adapter.out.persistence.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostJpaEntity, Long> {

}
