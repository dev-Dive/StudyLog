package com.devdive.backend.security.filter;

import com.devdive.backend.security.core.Authentication;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.SecurityContext;
import com.devdive.backend.security.core.SecurityContextHolder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Slf4j
public class AccessTokenValidationAndRevocationFilter implements Filter {
    private static final String HEADER = "bearer";
    private final AuthenticationCache authenticationCache;
    private final String secret;


    public AccessTokenValidationAndRevocationFilter(String secret, AuthenticationCache authenticationCache) {
        this.secret = secret;
        this.authenticationCache=authenticationCache;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);


        if(!header.startsWith(HEADER)){
            chain.doFilter(request,response);
            return;
        }

        String token = header.substring(HEADER.length()+1, header.length());
        String email = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        Authentication authentication =authenticationCache.findAuthentication(email);
        if(authentication!=null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}


