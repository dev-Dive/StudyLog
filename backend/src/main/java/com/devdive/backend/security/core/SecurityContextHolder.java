package com.devdive.backend.security.core;

public class SecurityContextHolder {
    private static SecurityContextHolderStrategyImp strategy;

    static {
        strategy=new SecurityContextHolderStrategyImp();
    }
    public static SecurityContext getContext() {
        return strategy.getContext();
    }

    public static void addContext(SecurityContext context){
        strategy.setContext(context);
    }

    public static void clearContext(){
        strategy.clearContext();
    }
}
