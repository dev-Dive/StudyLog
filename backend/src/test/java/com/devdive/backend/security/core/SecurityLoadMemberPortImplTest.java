package com.devdive.backend.security.core;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.security.authentication.adaptor.out.persistent.LoadMemberPortImpl;
import com.devdive.backend.security.authentication.adaptor.out.persistent.UserDataRepository;
import com.devdive.backend.security.authentication.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityLoadMemberPortImplTest {

    @Test
    @DisplayName("이메일로 사용자 검색 테스트")
    void findByEmailTest() {
        String mail = "rkdtjdqja@qkqh.com";
        UserDataRepository userDataRepository = mock(UserDataRepository.class);
        when(userDataRepository.findByMail(eq(mail)))
                .thenReturn(new MemberJpaEntity(1L,"name",mail,"profileUrl"));

        LoadMemberPortImpl loadMemberPort = new LoadMemberPortImpl(userDataRepository);

        User findData = loadMemberPort.findByMail(mail);

        assertThat(findData).isNotNull();
        assertThat(findData.getId()).isEqualTo(1);
        assertThat(findData.getUsername()).isEqualTo("rkdtjdqja@qkqh.com");
    }
}
