package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.core.Authentication;

import java.util.List;

public class RoleVoter implements AccessDecisionVoter {

    public int vote(Authentication authentication, List<String> roles) {
        String credentials = (String) authentication.getCredentials();
        for(String role:roles){
            if(credentials.equals(role)){
                return ACCESS_GRANTED;
            }
        }

        return ACCESS_DENIED;
    }
}
