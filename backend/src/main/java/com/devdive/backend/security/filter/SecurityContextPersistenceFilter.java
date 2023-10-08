package com.devdive.backend.security.filter;

import com.devdive.backend.security.core.SecurityContextHolder;
import jakarta.servlet.*;

import java.io.IOException;

public class SecurityContextPersistenceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            SecurityContextHolder.addContext(SecurityContextHolder.createEmptyContext());
            chain.doFilter(request,response);
        }finally {
            SecurityContextHolder.clearContext();
        }
    }
}
