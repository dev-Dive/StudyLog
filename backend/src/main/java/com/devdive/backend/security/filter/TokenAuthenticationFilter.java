package com.devdive.backend.security.filter;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.JwtTokenAuthenticationToken;
import com.devdive.backend.security.core.UserDetails;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.Map;


@Slf4j
public class TokenAuthenticationFilter implements Filter {

    private final UserDetailsService<String, UserDetails> userDetailsService;
    private final JwtProvider mailJwtProvider;
    private final JwtProvider accessJwtProvider;
    private final JwtProvider refreshJwtProvider;

    private final AntPathMatcher antPathMatcher;

    private final String patternUrl;

    private final AuthenticationCache authenticationCache;

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

        String token = getJsonValue(req, "token");

        if(token==null){
            failedAuthentication(res);
            return;
        }

        if (!mailJwtProvider.isValid(token)) {
            failedAuthentication(res);
            return;
        }

        String mail = mailJwtProvider.extractSubject(token);

        UserDetails member = userDetailsService.findMemberByEmail(mail);
        if (member == null) {
            failedAuthentication(res);
            return;
        }

        successAuthentication(res, mail, member);
    }

    private void successAuthentication(HttpServletResponse res, String mail, UserDetails member) throws IOException {
        JwtTokenAuthenticationToken authentication = new JwtTokenAuthenticationToken(member);
        authentication.setAuthenticated("MEMBERS");

        authenticationCache.addAuthentication(authentication);

        log.info("{} login success", mail);

        res.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(generateAuthToken(mail)));
    }

    private static void failedAuthentication(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    private String getJsonValue(HttpServletRequest req, String key) throws IOException {
        if(req.getContentType()==null){
            return null;
        }

        if(!req.getContentType().equals("application/json")){
            return null;
        }
        ServletInputStream inputStream = req.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> jsonMap = objectMapper.readValue(stringBuilder.toString(), new TypeReference<>() {
            });
            return jsonMap.get(key);
        } catch (MismatchedInputException e){
            return null;
        }
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
