package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.authentication.Authentication;

import java.util.List;

public class RoleVoter implements AccessDecisionVoter {

    public int vote(Authentication authentication, List<String> roles) {
        String credentials = authentication.getAuthorities();
        for(String role:roles){
            if(credentials.equals(role)){
                return ACCESS_GRANTED;
            }
        }

        return ACCESS_DENIED;
    }
}
