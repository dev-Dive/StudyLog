package com.devdive.backend.security.core;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

import java.util.List;

public class DefaultChainFilter implements SecurityFilterChain{
    private final AntPathMatcher antPathMatcher;
    private String pattern;

    private List<Filter> filters;
    public DefaultChainFilter(String pattern, List<Filter> filters) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern=pattern;
        this.filters = filters;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return antPathMatcher.match(pattern,request.getRequestURI());
    }

    @Override
    public List<Filter> getFilters() {
        return filters;
    }
}
