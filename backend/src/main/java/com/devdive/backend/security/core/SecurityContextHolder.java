package com.devdive.backend.security.core;

public class SecurityContextHolder {
    private static SecurityContextHolderStrategyImp strategy;

    public static SecurityContext getContext() {
        return strategy.getContext();
    }

    public static void addContext(SecurityContextImp context){
        strategy.setContext(context);
    }

    public static void clearContext(){
        strategy.clearContext();
    }
}
