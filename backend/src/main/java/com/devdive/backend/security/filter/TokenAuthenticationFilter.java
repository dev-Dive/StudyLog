package com.devdive.backend.security.filter;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.core.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


@Slf4j
public class TokenAuthenticationFilter implements Filter {

    private final UserDetailsService<String, UserDetails> userDetailsService;
    private final JwtProvider mailJwtProvider;
    private final JwtProvider accessJwtProvider;
    private final JwtProvider refreshJwtProvider;

    private final AntPathMatcher antPathMatcher;

    private final String patternUrl;

    private final AuthenticationCache authenticationCache;
    private final JsonHandler jsonHandler = new JsonHandler();

    public TokenAuthenticationFilter(
            UserDetailsService<String, UserDetails> userDetailsService, JwtProvider mailJwtProvider,
            JwtProvider accessJwtProvider, JwtProvider refreshJwtProvider,
            String patternUrl, AuthenticationCache authenticationCache) {
        this.userDetailsService = userDetailsService;
        this.mailJwtProvider = mailJwtProvider;
        this.accessJwtProvider = accessJwtProvider;
        this.refreshJwtProvider = refreshJwtProvider;
        this.patternUrl = patternUrl;
        this.antPathMatcher = new AntPathMatcher();
        this.authenticationCache = authenticationCache;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!isSurpported(req)) {
            chain.doFilter(request, response);
            return;
        }

        UserDetails member;
        try {
            member = attemptAuthentication(req);
            if(member==null){
                failedAuthentication(res);
                return;
            }

            JwtTokenAuthenticationToken authentication = new JwtTokenAuthenticationToken(member);
            authentication.setAuthenticated("MEMBERS");

            authenticationCache.addAuthentication(authentication);

        } catch (IllegalArgumentException e) {
            res.sendError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
            return;
        } catch (MismatchedInputException e) {
            res.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return;
        }catch (JsonParseException e){
            res.sendError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
            return;
        }

        successAuthentication(res,member.getUsername());
    }

    private UserDetails attemptAuthentication(HttpServletRequest req) throws IOException {
        if (req.getContentType() == null || !req.getContentType().equals("application/json")) {
            throw new IllegalArgumentException("잘못된 헤더 값");
        }

        String token = jsonHandler.getValue(getContent(req), "token");

        if (token == null || !mailJwtProvider.isValid(token)) {
            return null;
        }

        String mail = mailJwtProvider.extractSubject(token);

        return userDetailsService.findMemberByEmail(mail);
    }

    private static String getContent(HttpServletRequest req) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private void successAuthentication(HttpServletResponse res,String mail) throws IOException {

        res.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(generateAuthToken(mail)));
    }

    private static void failedAuthentication(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    private boolean isSurpported(HttpServletRequest req) {
        return antPathMatcher.match(patternUrl, req.getRequestURI()) && req.getMethod().equals("POST");
    }

    private AuthToken generateAuthToken(String mail) {
        String accessToken = accessJwtProvider.createJwtToken(mail);
        String refreshToken = refreshJwtProvider.createJwtToken(mail);

        return new AuthToken(accessToken, refreshToken);
    }

    @Data
    static class AuthToken {
        private final String accessToken;
        private final String refreshToken;
    }
}
