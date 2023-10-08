package com.devdive.backend.security.access.manager;

import com.devdive.backend.security.access.AccessDecisionManager;
import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.access.voter.RoleVoter;
import com.devdive.backend.security.authentication.Authentication;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class AccessDecisionManagerImp implements AccessDecisionManager {
    private List<AccessDecisionVoter> roleVoters;

    public AccessDecisionManagerImp(List<AccessDecisionVoter> roleVoters) {
        this.roleVoters = roleVoters;
    }
    @Override
    public void decide(Authentication authentication, List<String> roles) throws AccessDeniedException {
        for(AccessDecisionVoter voter:roleVoters){
            if(voter.vote(authentication,roles)== RoleVoter.ACCESS_GRANTED){
                return;
            }
        }

        throw new AccessDeniedException("권한이 없습니다.");
    }
}
