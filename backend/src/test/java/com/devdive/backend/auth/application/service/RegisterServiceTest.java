package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import com.devdive.backend.auth.application.port.out.persistence.UpdateMemberPort;
import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.auth.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class RegisterServiceTest {

    @Autowired
    JwtProvider mailJwtProvider;

    @MockBean
    LoadMemberPort loadMemberPort;

    @MockBean
    UpdateMemberPort updateMemberPort;

    @Autowired
    RegisterService registerService;

    @Test
    @DisplayName("유효한 토큰으로 회원가입을 하면 회원가입이 된고 인증 토큰을 발행한다.")
    void givenValidToken_whenRegister_thenReturnAuthTokenAndRegister() {
        String mail = "test@test.com";
        String registerToken = mailJwtProvider.createJwtToken(mail);

        when(loadMemberPort.isRegisteredMail(anyString()))
                .thenReturn(false);
        when(updateMemberPort.register(any(Member.class)))
                .thenReturn(true);

        var authToken = registerService.register(registerToken);

        verify(updateMemberPort).register(any(Member.class));
        assertThat(authToken.getAccessToken()).isNotBlank();
        assertThat(authToken.getRefreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("이미 회원가입이 되어 있다면 유효한 토큰으로 회원가입을 하면 인증토큰만 발행한다.")
    void givenValidTokenAndRegistered_whenRegister_thenReturnAuthTokenAndLogin() {
        String mail = "test@test.com";
        String registerToken = mailJwtProvider.createJwtToken(mail);

        when(loadMemberPort.isRegisteredMail(anyString()))
                .thenReturn(true);

        var authToken = registerService.register(registerToken);

        verify(updateMemberPort, never()).register(any(Member.class));
        assertThat(authToken.getAccessToken()).isNotBlank();
        assertThat(authToken.getRefreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("유효하지 않은 토큰으로 회원가입을 하면 회원가입에 실패한다.")
    void givenInvalidToken_whenRegister_thenThrowIllegalArgumentException() {
        String mail = "test@test.com";
        String registerToken = mailJwtProvider.createJwtToken(mail) + "1";

        assertThatThrownBy(() -> registerService.register(registerToken))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
