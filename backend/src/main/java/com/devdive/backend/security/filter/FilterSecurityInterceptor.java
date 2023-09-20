package com.devdive.backend.security.filter;

import com.devdive.backend.security.access.AccessDecisionManager;
import com.devdive.backend.security.core.Authentication;
import com.devdive.backend.security.core.SecurityContextHolder;
import com.devdive.backend.security.access.SecurityMetaDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
public class FilterSecurityInterceptor implements Filter {

    private SecurityMetaDataSource metaDataSource;

    private AccessDecisionManager accessDecisionManagerImp;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        List<String> roles = metaDataSource.getRoles((HttpServletRequest)request);
        if(roles.isEmpty()){
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try{
            this.accessDecisionManagerImp.decide(authentication,roles);
        }catch (AccessDeniedException accessDeniedException){
            accessDeniedException.printStackTrace();
            throw accessDeniedException;
        }

        chain.doFilter(request,response);
    }

    public void setMetaDataSource(SecurityMetaDataSource metaDataSource) {
        this.metaDataSource = metaDataSource;
    }

    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManagerImp) {
        this.accessDecisionManagerImp = accessDecisionManagerImp;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
