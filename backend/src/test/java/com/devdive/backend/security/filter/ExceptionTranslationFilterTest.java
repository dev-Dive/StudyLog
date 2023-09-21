package com.devdive.backend.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ExceptionTranslationFilterTest {

    @Test
    @DisplayName("ExceptionTranslationFilter 인가예외 변환 테스트")
    void testExceptionTranslationFilterAccessDeniedException() throws ServletException, IOException {
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter();
        ServletRequest request = new MockHttpServletRequest("GET", "/read");
        ServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        doThrow(new AccessDeniedException("권한 없음")).when(filterChain).doFilter(eq(request),eq(response));

        exceptionTranslationFilter.doFilter(request,response,filterChain);

        HttpServletResponse rep = (HttpServletResponse) response;
        Assertions.assertThat(rep.getStatus()).isEqualTo(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    @DisplayName("ExceptionTranslationFilter 인증예외 변환 테스트")
    void testExceptionTranslationFilterAuthenticationException() throws ServletException, IOException {
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter();
        ServletRequest request = new MockHttpServletRequest("GET", "/read");
        ServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        doThrow(new AuthenticationException("인증 되지않음")).when(filterChain).doFilter(eq(request),eq(response));

        exceptionTranslationFilter.doFilter(request,response,filterChain);

        HttpServletResponse rep = (HttpServletResponse) response;
        Assertions.assertThat(rep.getStatus()).isEqualTo(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
