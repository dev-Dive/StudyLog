package com.devdive.backend.security.filter;

import com.devdive.backend.security.access.SecurityMetaDataSource;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.securitycontext.SecurityContext;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FilterSecurityInterceptorTest {


    @Test
    @DisplayName("필터 인가 처리 성공 테스트")
    void authorizeFilterPassTest() throws ServletException, IOException {

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager((authentication, role) -> {});


        ServletRequest request = new MockHttpServletRequest("GET", "/read");
        ServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        try(MockedStatic<SecurityContextHolder> securityContextHolderMocked = mockStatic(SecurityContextHolder.class)) {
            securityContextHolderMocked.when(SecurityContextHolder::getContext).thenReturn(new SecurityContext() {
                @Override
                public Authentication getAuthentication() {
                    return mock(Authentication.class);
                }

                @Override
                public void setAuthentication(Authentication authentication) {

                }
            });

            SecurityMetaDataSource mockMetaDataSource = mock(SecurityMetaDataSource.class);
            when(mockMetaDataSource.getRoles(eq((HttpServletRequest)request))).thenReturn(List.of("READ"));
            filterSecurityInterceptor.setMetaDataSource(mockMetaDataSource);

            filterSecurityInterceptor.doFilter(request, response, filterChain);
        }


    }

    @Test
    @DisplayName("필터 인가 처리 실패 테스트")
    void authorizeFilterFailTest() {

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager((authentication, role) -> {
            throw new AccessDeniedException("권한 없음");
        });

        ServletRequest request = new MockHttpServletRequest("GET", "/read");
        ServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        try(MockedStatic<SecurityContextHolder> securityContextHolderMocked = mockStatic(SecurityContextHolder.class)) {
            securityContextHolderMocked.when(SecurityContextHolder::getContext).thenReturn(new SecurityContext() {
                @Override
                public Authentication getAuthentication() {
                    return mock(Authentication.class);
                }

                @Override
                public void setAuthentication(Authentication authentication) {

                }
            });

            SecurityMetaDataSource mockMetaDataSource = mock(SecurityMetaDataSource.class);
            when(mockMetaDataSource.getRoles(eq((HttpServletRequest)request))).thenReturn(List.of("READ"));
            filterSecurityInterceptor.setMetaDataSource(mockMetaDataSource);

            Assertions.assertThatThrownBy(() -> filterSecurityInterceptor.doFilter(request, response, filterChain))
                    .isInstanceOf(AccessDeniedException.class);
        }
    }

    @Test
    @DisplayName("인증 예외 테스트")
    void authenticationExceptionFailTest() {

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager((authentication, role) -> {});


        ServletRequest request = new MockHttpServletRequest("GET", "/read");
        ServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        SecurityMetaDataSource mockMetaDataSource = mock(SecurityMetaDataSource.class);
        when(mockMetaDataSource.getRoles(eq((HttpServletRequest)request))).thenReturn(List.of("READ"));
        filterSecurityInterceptor.setMetaDataSource(mockMetaDataSource);

        Assertions.assertThatThrownBy(() -> filterSecurityInterceptor.doFilter(request, response, filterChain))
                .isInstanceOf(AuthenticationException.class);
    }
}
