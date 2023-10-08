package com.devdive.backend.security.filter;

import com.devdive.backend.security.core.SecurityFilterChain;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FilterChainProxy extends GenericFilterBean {

    List<SecurityFilterChain> filterChainList;

    public FilterChainProxy(List<SecurityFilterChain> filterChainList) {
        this.filterChainList = filterChainList;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        List<Filter> filters=getFilters((HttpServletRequest) request);
        if(filters==null){
            chain.doFilter(request,response);
            return;
        }

        VirtualFilterChain vf=new VirtualFilterChain(chain,filters);
        vf.doFilter(request,response);
    }

    private List<Filter> getFilters(HttpServletRequest request) {
        for(SecurityFilterChain filterChain:filterChainList){
            if(filterChain.matches(request)){
                return filterChain.getFilters();
            }
        }

        return null;
    }

    private static class VirtualFilterChain implements FilterChain {
        private final FilterChain originChain;
        private final List<Filter> filters;
        private final int size;
        private int currentPosition=0;
        public VirtualFilterChain(FilterChain chain, List<Filter> filters) {
            this.originChain=chain;
            this.filters=filters;
            this.size=filters.size();
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
            if(size==currentPosition){
                originChain.doFilter(request,response);
            }else{
                filters.get(currentPosition++).doFilter(request,response,this);
            }
        }
    }
}
