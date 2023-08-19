package com.devdive.backend.hello.adaptor.out.persistance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void test() {
        HelloJpaEntity entity = new HelloJpaEntity();

        helloRepository.save(entity);

        List<HelloJpaEntity> all = helloRepository.findAll();

        assertThat(all.size()).isNotZero();
        assertThat(all.get(0).getId()).isEqualTo(1);
    }
}
