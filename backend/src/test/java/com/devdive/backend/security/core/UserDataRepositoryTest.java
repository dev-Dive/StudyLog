package com.devdive.backend.security.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static com.devdive.backend.security.core.LoadMemberPort.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserDataRepositoryTest {

    @Autowired
    private UserDataRepository userDataRepository;

    @Test
    @Transactional
    @DisplayName("이메일로 사용자 검색 성공 테스트")
    void findByEmailSuccessTest() {
        userDataRepository.save(new UserData(1L, "rkdtjdqja@qkqh.com"));

        UserData findUser = userDataRepository.findByMail("rkdtjdqja@qkqh.com");

        assertThat(findUser).isNotNull();
        assertThat(findUser.getId()).isEqualTo(1);
        assertThat(findUser.getMail()).isEqualTo("rkdtjdqja@qkqh.com");
    }

    @Test
    @Transactional
    @DisplayName("이메일로 사용자 검색 실패 테스트")
    void findByEmailFailTest() {
        UserData findUser = userDataRepository.findByMail("rkdtjdqja@qkqh.com");

        assertThat(findUser).isNull();
    }
}
