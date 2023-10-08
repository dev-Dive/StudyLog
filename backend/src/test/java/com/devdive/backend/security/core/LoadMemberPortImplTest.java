package com.devdive.backend.security.core;

import com.devdive.backend.security.authentication.adaptor.out.persistent.LoadMemberPortImpl;
import com.devdive.backend.security.authentication.adaptor.out.persistent.UserDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.devdive.backend.security.authentication.application.port.out.LoadMemberPort.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoadMemberPortImplTest {

    @Test
    @DisplayName("이메일로 사용자 검색 테스트")
    void findByEmailTest() {
        String mail = "rkdtjdqja@qkqh.com";
        UserDataRepository userDataRepository = mock(UserDataRepository.class);
        when(userDataRepository.findByMail(eq(mail))).thenReturn(new UserData(1L, "rkdtjdqja@qkqh.com"));

        LoadMemberPortImpl loadMemberPort = new LoadMemberPortImpl(userDataRepository);

        UserData findData = loadMemberPort.findByEmail(mail);

        assertThat(findData).isNotNull();
        assertThat(findData.getId()).isEqualTo(1);
        assertThat(findData.getMail()).isEqualTo("rkdtjdqja@qkqh.com");
    }
}
