package com.devdive.backend.security.filter;

import com.devdive.backend.security.core.Authentication;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.SecurityContext;
import com.devdive.backend.security.core.SecurityContextHolder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AccessTokenValidationAndRevocationFilterTest {

    @Test
    @DisplayName("필터 검증 성공 테스트")
    public void TokenValidationSuccessTest() throws ServletException, IOException {

        //given
        String secret = "devdive";

        Date iat = new Date(System.currentTimeMillis());
        String jwtToken = Jwts.builder()
                .setHeader(new HashMap<>(Map.of("typ", "JWT", "alg", "HS256")))
                .setSubject("test@gmail.com")
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + getHour(1)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION,"bearer "+ jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache=mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(secret, authenticationCache);

        //when
        accessTokenValidationAndRevocationFilter.doFilter(request,response,filterChain);

        //then
        verify(filterChain,times(1)).doFilter(eq(request),eq(response));
    }

    @Test
    @DisplayName("필터 검증 실패 테스트")
    public void TokenValidationFailTest() throws ServletException, IOException {

        //given
        String secret = "devdive";

        Date iat = new Date(System.currentTimeMillis());
        String jwtToken = Jwts.builder()
                .setHeader(new HashMap<>(Map.of("typ", "JWT", "alg", "HS256")))
                .setSubject("test@gmail.com")
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + getHour(1)))
                .signWith(SignatureAlgorithm.HS256, secret+"s")
                .compact();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION,"bearer "+ jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache=mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(secret, authenticationCache);

        //when
        assertThatThrownBy(()->accessTokenValidationAndRevocationFilter.doFilter(request,response,filterChain))

                //then
                .isInstanceOf(SignatureException.class);
    }

    @Test
    @DisplayName("토큰 만료 테스트")
    public void tokenExpirationTest() throws ServletException, IOException {

        //given
        String secret = "devdive";

        Date iat = new Date(System.currentTimeMillis()-((getHour(1))+1));
        String jwtToken = Jwts.builder()
                .setHeader(new HashMap<>(Map.of("typ", "JWT", "alg", "HS256")))
                .setSubject("test@gmail.com")
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + getHour(1)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION,"bearer "+ jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache=mock(AuthenticationCache.class);
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(secret, authenticationCache);

        //when
        assertThatThrownBy(()->accessTokenValidationAndRevocationFilter.doFilter(request,response,filterChain))

                //then
                .isInstanceOf(ExpiredJwtException.class);
    }

    @Test
    @DisplayName("SecurityContext 저장 테스트")
    public void SecurityContextSaveTest() throws ServletException, IOException {

        //given
        String secret = "devdive";

        Date iat = new Date(System.currentTimeMillis());
        String email = "test@gmail.com";
        String jwtToken = Jwts.builder()
                .setHeader(new HashMap<>(Map.of("typ", "JWT", "alg", "HS256")))
                .setSubject(email)
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + getHour(1)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION,"bearer "+ jwtToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        AuthenticationCache authenticationCache=mock(AuthenticationCache.class);
        when(authenticationCache.findAuthentication(eq(email))).thenReturn(mock(Authentication.class));

        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter = getAccessTokenValidationAndRevocationFilter(secret, authenticationCache);

        try(MockedStatic<SecurityContextHolder> mockContextHolder = mockStatic(SecurityContextHolder.class)) {
            TestSecurityContext testSecurityContext = new TestSecurityContext();
            mockContextHolder.when(SecurityContextHolder::getContext).thenReturn(testSecurityContext);

            //when
            accessTokenValidationAndRevocationFilter.doFilter(request,response,filterChain);

            //then
            mockContextHolder.verify(SecurityContextHolder::getContext,times(1));
            assertThat(testSecurityContext.getAuthentication()).isNotNull();
        }
    }

    private static AccessTokenValidationAndRevocationFilter getAccessTokenValidationAndRevocationFilter(String secret, AuthenticationCache authenticationCache) {
        AccessTokenValidationAndRevocationFilter accessTokenValidationAndRevocationFilter=new AccessTokenValidationAndRevocationFilter(secret, authenticationCache);
        return accessTokenValidationAndRevocationFilter;
    }

    private static int getHour(int hour) {
        return 1_000 * 60 * 60 * hour;
    }

    static class TestSecurityContext implements SecurityContext {

        Authentication authentication;

        @Override
        public Authentication getAuthentication() {
            return authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication=authentication;
        }
    }
}
