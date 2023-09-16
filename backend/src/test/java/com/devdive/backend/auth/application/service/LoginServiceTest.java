package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.in.LoginUseCase;
import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import com.devdive.backend.auth.application.port.out.persistence.UpdateMemberPort;
import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class LoginServiceTest {

    @Autowired
    JwtProvider mailJwtProvider;

    @Autowired
    LoginService loginService;

    @MockBean
    LoadMemberPort loadMemberPort;

    @MockBean
    UpdateMemberPort updateMemberPort;

    @Test
    @DisplayName("유효한 토큰으로 로그인 시 인증 토큰이 발행된다.")
    void givenValidToken_whenLogin_thenReturnAuthToken() {
        String mail = "test@test.com";
        String token = mailJwtProvider.createJwtToken(mail);

        when(loadMemberPort.isRegisteredMail(anyString()))
                .thenReturn(true);
        LoginUseCase.AuthToken authToken = loginService.login(token);

        assertThat(authToken.getAccessToken()).isNotBlank();
        assertThat(authToken.getRefreshToken()).isNotBlank();
    }
}
