package com.devdive.backend.security.access;

import com.devdive.backend.security.authentication.Authentication;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AccessDecisionManager {
    void decide(Authentication authentication, List<String> roles) throws AccessDeniedException;
}
