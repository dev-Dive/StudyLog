package com.devdive.backend.persistance.repository;

import com.devdive.backend.persistance.entities.PostJpaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface PostRepository extends JpaRepository<PostJpaEntity, Long> {

}
