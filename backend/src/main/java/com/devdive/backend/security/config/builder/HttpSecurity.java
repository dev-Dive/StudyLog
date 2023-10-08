package com.devdive.backend.security.config.builder;

import com.devdive.backend.security.config.builder.configur.*;
import com.devdive.backend.security.authentication.application.port.in.UserDetailsService;
import com.devdive.backend.security.core.DefaultChainFilter;
import jakarta.servlet.Filter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HttpSecurity {
    private String pattern="/**";
    private List<Filter> filters=new ArrayList<>();
    private final LinkedHashMap<Class<? extends SecurityConfigurer>,SecurityConfigurer> configurers= new LinkedHashMap<>();
    private FilterComparator comparator=new FilterComparator();

    private UserDetailsService<String> detailsService;

    public HttpSecurity(UserDetailsService<String> detailsService) {
        this.detailsService = detailsService;
    }

    public SecurityContextConfigurer securityContext(){
        return (SecurityContextConfigurer) getOrApply(new SecurityContextConfigurer());
    }

    public AnonymousConfigurer anonymous(){
        return (AnonymousConfigurer) getOrApply(new AnonymousConfigurer());
    }

    public ExceptionHandlingConfigurer exceptionHandling(){
        return (ExceptionHandlingConfigurer) getOrApply(new ExceptionHandlingConfigurer());
    }

    public HttpSecurity cors(){
        getOrApply(new CorsConfigurer());
        return this;
    }

    public AccessTokenValidConfigurer accessTokenValid(){
        return (AccessTokenValidConfigurer) getOrApply(new AccessTokenValidConfigurer());
    }

    public TokenLoginConfigurer tokenLogin(){
        TokenLoginConfigurer tokenLoginConfigurer = new TokenLoginConfigurer();
        tokenLoginConfigurer.setUserDetailService(detailsService);
        return (TokenLoginConfigurer) getOrApply(tokenLoginConfigurer);
    }

    public ExpressionUrlAuthorizationConfigurer authorizeRequests(){
        return (ExpressionUrlAuthorizationConfigurer) getOrApply(new ExpressionUrlAuthorizationConfigurer());
    }

    public HttpSecurity antMatcher(String pattern){
        this.pattern=pattern;
        return this;
    }

    private SecurityConfigurer getOrApply(SecurityConfigurer securityConfigurer) {
        Class<? extends SecurityConfigurer> clz = securityConfigurer.getClass();

        if(securityConfigurer instanceof AbstractSecurityConfigurer){
            synchronized (configurers) {
                AbstractSecurityConfigurer configurer = (AbstractSecurityConfigurer) configurers.getOrDefault(clz, securityConfigurer);
                configurer.setBuilder(this);
                configurers.put(clz, configurer);
            }
        }

        return configurers.get(clz);
    }

    public DefaultChainFilter build(){
        for(SecurityConfigurer configurer:configurers.values()){
            configurer.init();
            configurer.config(this);
        }

        filters.sort(comparator);

        return new DefaultChainFilter(pattern,filters);
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }
}
