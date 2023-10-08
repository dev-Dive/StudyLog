package com.devdive.backend.security.core.securitycontext;

public interface SecurityContextHolderStrategy {

    SecurityContext getContext();

    void setContext(SecurityContext context);

    SecurityContext createEmptyContext();
    void clearContext();
}
