package com.devdive.backend.hello.adaptor.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<HelloJpaEntity, Long> {
}
