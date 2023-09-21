package com.devdive.backend.security.core;

public interface SecurityContextHolderStrategy {

    SecurityContext getContext();

    void setContext(SecurityContext context);

    SecurityContext createEmptyContext();
    void clearContext();
}
