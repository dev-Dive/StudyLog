package com.devdive.backend.security.filter;

import com.devdive.backend.security.authentication.AnonymousAuthenticationToken;
import com.devdive.backend.security.core.Authentication;
import com.devdive.backend.security.core.SecurityContext;
import com.devdive.backend.security.core.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AnonymousAuthenticationFilterTest {


    @Test
    @DisplayName("익명 사용자 생성 테스트")
    void anonymousUserGenerateTest() throws ServletException, IOException {
        AnonymousAuthenticationFilter anonymousAuthenticationFilter
                =new AnonymousAuthenticationFilter("anonymousUser","ANONVMOUS");
        try(MockedStatic<SecurityContextHolder> securityContextHolderMocked = mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = new SecurityContext() {
                Authentication authentication;
                @Override
                public Authentication getAuthentication() {
                    return authentication;
                }

                @Override
                public void setAuthentication(Authentication authentication) {
                    this.authentication=authentication;
                }
            };
            securityContextHolderMocked.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            ServletRequest request = new MockHttpServletRequest();
            ServletResponse response = new MockHttpServletResponse();
            FilterChain filterChain = mock(FilterChain.class);
            anonymousAuthenticationFilter.doFilter(request,response,filterChain);

            securityContextHolderMocked.verify(SecurityContextHolder::getContext,times(2));
            Assertions.assertThat(securityContext.getAuthentication().getAuthorities()).isEqualTo("ANONVMOUS");
        }
    }

    @Test
    @DisplayName("익명 사용자 비 생성 테스트")
    void anonymousUserNonGenerateTest() throws ServletException, IOException {
        AnonymousAuthenticationFilter anonymousAuthenticationFilter
                =new AnonymousAuthenticationFilter("anonymousUser","ANONVMOUS");
        try(MockedStatic<SecurityContextHolder> securityContextHolderMocked = mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = new SecurityContext() {
                Authentication authentication=new AnonymousAuthenticationToken("anonymousUser","ANONVMOUS");
                @Override
                public Authentication getAuthentication() {
                    return authentication;
                }

                @Override
                public void setAuthentication(Authentication authentication) {
                    this.authentication=authentication;
                }
            };
            securityContextHolderMocked.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            ServletRequest request = new MockHttpServletRequest();
            ServletResponse response = new MockHttpServletResponse();
            FilterChain filterChain = mock(FilterChain.class);
            anonymousAuthenticationFilter.doFilter(request,response,filterChain);

           securityContextHolderMocked.verify(SecurityContextHolder::getContext,times(1));
        }
    }
}
