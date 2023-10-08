package com.devdive.backend.security.filter;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.securitycontext.SecurityContext;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AccessTokenValidationAndRevocationFilterTest {

    private static final String secret = generateSecret();
    private static final JwtProvider jwtProvider = new JwtProvider(secret, 1);

    @Test
    @DisplayName("필터 검증 성공 테스트")
    public void TokenValidationSuccessTest() throws ServletException, IOException {
        String mail = "test@gmail.com";
        String jwtToken = jwtProvider.createJwtToken(mail);


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache = mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(authenticationCache);

        //when
        accessTokenValidationAndRevocationFilter.doFilter(request, response, filterChain);

        //then
        verify(filterChain, times(1)).doFilter(eq(request), eq(response));
    }

    @Test
    @DisplayName("필터 검증 실패 테스트")
    public void TokenValidationFailTest() throws ServletException, IOException {

        //given
        String mail = "test@gmail.com";
        String jwtToken = jwtProvider.createJwtToken(mail);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken + "aaaa");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache = mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(authenticationCache);
        accessTokenValidationAndRevocationFilter.doFilter(request, response, filterChain);

        //when
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    @DisplayName("토큰 만료 테스트")
    public void tokenExpirationTest() throws ServletException, IOException {

        //given
        String mail = "test@gmail.com";
        JwtProvider jwtProvider1 = new JwtProvider(secret, 0);
        String jwtToken = jwtProvider1.createJwtToken(mail);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache = mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(authenticationCache);
        accessTokenValidationAndRevocationFilter.doFilter(request, response, filterChain);

        //when
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("SecurityContext 저장 테스트")
    public void SecurityContextSaveTest() throws ServletException, IOException {

        //given
        String mail = "test@gmail.com";
        String jwtToken = jwtProvider.createJwtToken(mail);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache = mock(AuthenticationCache.class);
        when(authenticationCache.findAuthentication(eq(mail))).thenReturn(mock(Authentication.class));

        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(authenticationCache);

        try (MockedStatic<SecurityContextHolder> mockContextHolder = mockStatic(SecurityContextHolder.class)) {
            TestSecurityContext testSecurityContext = new TestSecurityContext();
            mockContextHolder.when(SecurityContextHolder::getContext).thenReturn(testSecurityContext);

            //when
            accessTokenValidationAndRevocationFilter.doFilter(request, response, filterChain);

            //then
            mockContextHolder.verify(SecurityContextHolder::getContext, times(1));
            assertThat(testSecurityContext.getAuthentication()).isNotNull();
        }
    }

    private static AccessTokenValidationAndRevocationFilter getAccessTokenValidationAndRevocationFilter(AuthenticationCache authenticationCache) {
        return new AccessTokenValidationAndRevocationFilter(jwtProvider, authenticationCache);
    }

    static class TestSecurityContext implements SecurityContext {

        Authentication authentication;

        @Override
        public Authentication getAuthentication() {
            return authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

    private static String generateSecret() {
        Random random = new Random();
        return random.ints('0', 'z')
                .filter(value -> ('0' <= value && value <= '9') || ('A' <= value && value <= 'Z') || ('a' <= value && value <= 'z'))
                .limit((255 / 2) + 1).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
