package com.devdive.backend.post.adapter.out.persistence.repository;

import com.devdive.backend.post.adapter.out.persistence.PostAuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAuthorsRepository extends JpaRepository<PostAuthorsEntity, Long> {
}
