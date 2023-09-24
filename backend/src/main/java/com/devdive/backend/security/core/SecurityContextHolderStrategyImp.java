package com.devdive.backend.security.core;

public class SecurityContextHolderStrategyImp implements SecurityContextHolderStrategy {
    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

    public SecurityContext getContext() {
        SecurityContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    public void setContext(SecurityContext context) {
        contextHolder.set(context);
    }

    public SecurityContext createEmptyContext() {
        return new SecurityContextImp();
    }

    public void clearContext() {
        contextHolder.remove();
    }
}
