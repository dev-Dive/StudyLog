package com.devdive.backend.security.access;

import com.devdive.backend.security.core.Authentication;

import java.util.List;

public interface AccessDecisionVoter {
    Integer ACCESS_GRANTED = 1;
    Integer ACCESS_DENIED = -1;
    int vote(Authentication authentication, List<String> roles);

}
