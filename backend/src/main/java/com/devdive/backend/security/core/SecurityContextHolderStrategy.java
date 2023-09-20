package com.devdive.backend.security.core;

public interface SecurityContextHolderStrategy {

    SecurityContext getContext();

    void setContext(SecurityContextImp context);

    SecurityContext createEmptyContext();
    void clearContext();
}
