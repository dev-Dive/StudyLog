package com.devdive.backend.security.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultUserDetailsServiceTest {

    @Test
    @DisplayName("사용자가 존재할때 UserDetails반환")
    void givenExitMember_thenReturnUserDetails() {
        LoadMemberPort loadMemberPort = mock(LoadMemberPort.class);
        UserDetailsService<String> userDetailsService = new DefaultUserDetailsService<>(loadMemberPort);

        String mail = "mail@mail.com";
        long id = 1L;
        when(loadMemberPort.findByEmail(eq(mail))).thenReturn(new LoadMemberPort.UserData(id, mail));
        UserDetails userDetails = userDetailsService.findMemberByEmail(mail);

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(mail);
        assertThat(userDetails.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("사용자가 존재하지 않을때 null반환")
    void givenNotExitMember_thenReturnNull() {
        LoadMemberPort loadMemberPort = mock(LoadMemberPort.class);
        UserDetailsService<String> userDetailsService = new DefaultUserDetailsService<>(loadMemberPort);

        String mail = "mail@mail.com";
        long id = 1L;
        when(loadMemberPort.findByEmail(eq(mail))).thenReturn(new LoadMemberPort.UserData(id, mail));
        UserDetails userDetails = userDetailsService.findMemberByEmail("mails@mail.com");

        assertThat(userDetails).isNull();
    }
}
