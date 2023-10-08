package com.devdive.backend.security.filter;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.securitycontext.SecurityContext;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class AccessTokenValidationAndRevocationFilter implements Filter {
    private static final String HEADER = "bearer";
    private final AuthenticationCache authenticationCache;
    private final JwtProvider jwtProvider;


    public AccessTokenValidationAndRevocationFilter(JwtProvider jwtProvider, AuthenticationCache authenticationCache) {
        this.jwtProvider = jwtProvider;
        this.authenticationCache=authenticationCache;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null){
            chain.doFilter(request,response);
            return;
        }

        String substring = header.substring(0, HEADER.length());

        if(!substring.equalsIgnoreCase(HEADER)){
            chain.doFilter(request,response);
            return;
        }

        String token = header.substring(HEADER.length()+1, header.length());

        if(!jwtProvider.isValid(token)){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return;
        }

        String mail = jwtProvider.extractSubject(token);
        Authentication authentication =authenticationCache.findAuthentication(mail);
        if(authentication!=null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}


