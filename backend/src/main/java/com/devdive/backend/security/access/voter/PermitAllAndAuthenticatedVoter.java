package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.core.Authentication;

import java.util.List;

public class PermitAllAndAuthenticatedVoter implements AccessDecisionVoter {
    @Override
    public int vote(Authentication authentication, List<String> roles) {
        for(String role:roles){
            if(role.equals("permitAll")){
                return AccessDecisionVoter.ACCESS_GRANTED;
            }
            if(role.equals("authenticated")){
                if(authentication.getAuthorities().equals("ANONYMOUS")){
                    return AccessDecisionVoter.ACCESS_DENIED;
                }else {
                    return AccessDecisionVoter.ACCESS_GRANTED;
                }
            }
        }


        return AccessDecisionVoter.ACCESS_DENIED;
    }
}
