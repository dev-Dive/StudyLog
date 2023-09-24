package com.devdive.backend.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FilterChainProxyTest {

    @Test
    @DisplayName("필터 체인 프록시 필터 선택 테스트")
    void doFilter() throws ServletException, IOException {

        SecurityFilterChain securityFilterChain1=new TestSecurityFilterChain("/user/**",List.of(new TestFilter()));
        SecurityFilterChain securityFilterChain2=new TestSecurityFilterChain("/**",List.of(new TestFilter()));
        FilterChainProxy filterChainProxy = new FilterChainProxy(List.of(securityFilterChain1, securityFilterChain2));

        MockHttpServletRequest request = new MockHttpServletRequest("GET","/user/post");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        filterChainProxy.doFilter(request,response,filterChain);

        TestFilter userFilter =(TestFilter) securityFilterChain1.getFilters().get(0);
        assertThat(userFilter.isDo).isTrue();

        TestFilter allFilter =(TestFilter) securityFilterChain2.getFilters().get(0);
        assertThat(allFilter.isDo).isFalse();
    }

    private static class TestSecurityFilterChain implements SecurityFilterChain{

        private String url;
        private AntPathMatcher antPathMatcher=new AntPathMatcher();
        private List<Filter> filters;

        public TestSecurityFilterChain(String url, List<Filter> filters) {
            this.url = url;
            this.filters = filters;
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            return antPathMatcher.match(url,request.getRequestURI());
        }

        @Override
        public List<Filter> getFilters() {
            return filters;
        }
    }

    private static class TestFilter implements Filter{
        private boolean isDo=false;

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            isDo=true;
        }

        public boolean isDo() {
            return isDo;
        }

    }
}