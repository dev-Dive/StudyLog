package com.devdive.backend.security.filter;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.UserDetails;
import com.devdive.backend.security.core.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.apache.http.entity.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TokenAuthenticationFilterTest {

    private static final String patternUrl = "/api/v1/auth/mail/login";
    private static final String method = "POST";

    @Test
    @DisplayName("유효한 토큰으로 로그인 시 인증 토큰이 발행된다.")
    void givenValidToken_whenLogin_thenReturnAuthToken() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        String mail = "test@test.com";
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetailsServiceAndDefineAct(mail);

        String testAccessToken = "testAccessToken";
        JwtProvider accessTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testAccessToken);

        String testRefreshToken = "testRefreshToken";
        JwtProvider refreshTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testRefreshToken);

        AuthenticationCache authenticationCache = mock(AuthenticationCache.class);
        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, authenticationCache);

        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        JSONObject responseContent = new JSONObject(response.getContentAsString());
        assertThat(responseContent.get("accessToken")).isEqualTo(testAccessToken);
        assertThat(responseContent.get("refreshToken")).isEqualTo(testRefreshToken);
        verify(authenticationCache, times(1)).addAuthentication(any());
    }

    @Test
    @DisplayName("만료된 토큰으로 로그인 시 401로 에러를 전송한다.")
    void expirationToken_whenLogin_thenSend401HttpStatus() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 0);

        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetails();

        JwtProvider accessTokenJwtProvider = generateJwtProvider();

        JwtProvider refreshTokenJwtProvider = generateJwtProvider();

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        String mail = "test@test.com";
        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("위조된 토큰으로 로그인 시 401로 에러를 전송한다.")
    void forgaveToken_whenLogin_thenSend401HttpStatus() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetails();

        JwtProvider accessTokenJwtProvider = generateJwtProvider();

        JwtProvider refreshTokenJwtProvider = generateJwtProvider();

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        String mail = "test@test.com";
        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail) + "aaaaaaaaaa");

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("바디 비 전송시 400로 에러를 전송한다.")
    void whenNotTransmitToken_thenSend400HttpStatus() throws ServletException, IOException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetails();

        JwtProvider accessTokenJwtProvider = generateJwtProvider();

        JwtProvider refreshTokenJwtProvider = generateJwtProvider();

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        MockHttpServletRequest request = new MockHttpServletRequest(method, patternUrl);
        request.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Json 잘못 전송시 401로 에러를 전송한다.")
    void whenInvalidJson_thenReturns401HttpStatus() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        String mail = "test@test.com";
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetailsServiceAndDefineAct(mail);

        JwtProvider accessTokenJwtProvider = generateJwtProvider();

        JwtProvider refreshTokenJwtProvider = generateJwtProvider();

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        JSONObject requestContent = new JSONObject();
        requestContent.put("tokens", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("ContentType이 Json이 아니라면 415로 에러를 전송한다.")
    void whenContentTypeIsNotJson_thenReturns415HttpStatus() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        String mail = "test@test.com";
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetailsServiceAndDefineAct(mail);

        JwtProvider accessTokenJwtProvider = generateJwtProvider();

        JwtProvider refreshTokenJwtProvider = generateJwtProvider();

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        request.setContentType(ContentType.TEXT_PLAIN.getMimeType());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    @DisplayName("login의 요청이 POST요청이 아니라면 인증을 실행하지 않는다.")
    void whenNonPOSTLoginRequest_thenAuthenticationNotExecuted() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        String mail = "test@test.com";
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetailsServiceAndDefineAct(mail);

        String testAccessToken = "testAccessToken";
        JwtProvider accessTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testAccessToken);

        String testRefreshToken = "testRefreshToken";
        JwtProvider refreshTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testRefreshToken);

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, patternUrl, mock(AuthenticationCache.class));

        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        request.setMethod("GET");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(eq(request), eq(response));
    }

    @Test
    @DisplayName("login url이 아닌다른 url접근시 인증을 실행하지 않는다.")
    void whenNonLoginURL_thenAuthenticationNotExecuted() throws ServletException, IOException, JSONException {

        JwtProvider mailJwtProvider = new JwtProvider(generateSecret(), 1);

        String mail = "test@test.com";
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetailsServiceAndDefineAct(mail);

        String testAccessToken = "testAccessToken";
        JwtProvider accessTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testAccessToken);

        String testRefreshToken = "testRefreshToken";
        JwtProvider refreshTokenJwtProvider = generateMockJwtProviderAndDefineAct(mail, testRefreshToken);

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(userDetailsService, mailJwtProvider
                        , accessTokenJwtProvider, refreshTokenJwtProvider, "/api/v1/auth/mail/logout", mock(AuthenticationCache.class));

        JSONObject requestContent = new JSONObject();
        requestContent.put("token", mailJwtProvider.createJwtToken(mail));

        MockHttpServletRequest request = generateMcokRequest(requestContent.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        tokenAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(eq(request), eq(response));
    }

    private UserDetailsService<String, UserDetails> generateMockUserDetailsServiceAndDefineAct(String mail) {
        UserDetailsService<String, UserDetails> userDetailsService = generateMockUserDetails();
        defineUserDetailsFindMemberByEmail(mail, userDetailsService);
        return userDetailsService;
    }

    private void defineUserDetailsFindMemberByEmail(String mail, UserDetailsService<String, UserDetails> userDetailsService) {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(mail);
        when(userDetailsService.findMemberByEmail(eq(mail))).thenReturn(userDetails);
    }

    private UserDetailsService<String, UserDetails> generateMockUserDetails() {
        return (UserDetailsService<String, UserDetails>) mock(UserDetailsService.class);
    }

    private MockHttpServletRequest generateMcokRequest(String requestContent) {
        MockHttpServletRequest request = new MockHttpServletRequest(method, patternUrl);
        request.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setContent(requestContent.getBytes(StandardCharsets.UTF_8));
        return request;
    }

    private JwtProvider generateMockJwtProviderAndDefineAct(String mail, String testAccessToken) {
        JwtProvider accessTokenJwtProvider = generateJwtProvider();
        defineJwtProviderActForCreateToken(mail, testAccessToken, accessTokenJwtProvider);
        return accessTokenJwtProvider;
    }

    private JwtProvider generateJwtProvider() {
        return mock(JwtProvider.class);
    }

    private void defineJwtProviderActForCreateToken(String mail, String testAccessToken, JwtProvider accessTokenJwtProvider) {
        when(accessTokenJwtProvider.createJwtToken(eq(mail))).thenReturn(testAccessToken);
    }

    private String generateSecret() {
        Random random = new Random();
        return random.ints('0', 'z')
                .filter(value -> ('0' <= value && value <= '9') || ('A' <= value && value <= 'Z') || ('a' <= value && value <= 'z'))
                .limit((255 / 2) + 1).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
