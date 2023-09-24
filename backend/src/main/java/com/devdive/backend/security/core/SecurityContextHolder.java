package com.devdive.backend.security.core;

public class SecurityContextHolder {
    private static SecurityContextHolderStrategy strategy;

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

    public  static SecurityContext  createEmptyContext(){
        return strategy.createEmptyContext();
    }
}
