package com.devdive.backend.security.config.builder.configur;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.access.SecurityMetaDataSource;
import com.devdive.backend.security.access.manager.AccessDecisionManagerImp;
import com.devdive.backend.security.access.voter.PermitAllAndAuthenticatedVoter;
import com.devdive.backend.security.access.voter.RoleHierarchyVoter;
import com.devdive.backend.security.access.voter.RoleVoter;
import com.devdive.backend.security.config.builder.HttpSecurity;
import com.devdive.backend.security.filter.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpressionUrlAuthorizationConfigurer extends AbstractSecurityConfigurer {
    private LinkedHashMap<String,List<String>> metaData =new LinkedHashMap<>();
    private boolean roleHierarchy=false;
    private String roleHierarchyPattern;

    @Override
    public void config(HttpSecurity http) {
        SecurityMetaDataSource securityMetaDataSource = new SecurityMetaDataSource(metaData);
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setMetaDataSource(securityMetaDataSource);

        ArrayList<AccessDecisionVoter> voters = new ArrayList<>();
        voters.add(new RoleVoter());
        if(roleHierarchy){
            voters.add(new RoleHierarchyVoter(roleHierarchyPattern));
        }
        voters.add(new PermitAllAndAuthenticatedVoter());
        filterSecurityInterceptor.setAccessDecisionManager(new AccessDecisionManagerImp(voters));
        http.addFilter(filterSecurityInterceptor);
    }

    public MvcMatchersAuthorizedUrl anyRequest(){
        return mvcMatchers("/**");
    }

    public MvcMatchersAuthorizedUrl mvcMatchers(String pattern){
        return new MvcMatchersAuthorizedUrl(pattern);
    }

    public ExpressionUrlAuthorizationConfigurer activateRoleHierarchy(String roleHierarchyPattern){
        this.roleHierarchyPattern=roleHierarchyPattern;
        roleHierarchy=true;
        return this;
    }

    public class MvcMatchersAuthorizedUrl {
        String url;

        public MvcMatchersAuthorizedUrl(String url) {
            this.url = url;
        }

        public ExpressionUrlAuthorizationConfigurer authenticated(){
            return hasRole("authenticated");
        }

        public ExpressionUrlAuthorizationConfigurer permitAll(){
            return hasRole("permitAll");
        }

        public ExpressionUrlAuthorizationConfigurer hasRole(String...roles){
            metaData.put(url,new ArrayList<>(List.of(roles)));
            return ExpressionUrlAuthorizationConfigurer.this;
        }
    }
}
