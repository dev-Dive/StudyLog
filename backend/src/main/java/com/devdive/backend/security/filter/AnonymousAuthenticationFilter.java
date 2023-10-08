package com.devdive.backend.security.filter;

import com.devdive.backend.security.authentication.token.AnonymousAuthenticationToken;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import jakarta.servlet.*;

import java.io.IOException;

public class AnonymousAuthenticationFilter implements Filter {
    private Object principal;

    private String authorities;
    public AnonymousAuthenticationFilter(Object principal, String authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(SecurityContextHolder.getContext().getAuthentication()==null){
            SecurityContextHolder.getContext().setAuthentication(createAuthenticationToken());
        }
        chain.doFilter(request,response);
    }

    private Authentication createAuthenticationToken() {
        return  new AnonymousAuthenticationToken(principal, authorities);
    }
}
