package com.devdive.backend.security.access;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SecurityMetaDataSource {
    private LinkedHashMap<String,List<String>> requestMap;
    private AntPathMatcher matcher;

    public SecurityMetaDataSource(LinkedHashMap<String,List<String>> requestMap) {
        this.requestMap=requestMap;
        matcher=new AntPathMatcher();
    }

    public List<String> getRoles(HttpServletRequest request) {
        for(Map.Entry<String, List<String>> entry:requestMap.entrySet()){
            if(matcher.match(entry.getKey(),request.getRequestURI())){
                return entry.getValue();
            }
        }
        return null;
    }
}
