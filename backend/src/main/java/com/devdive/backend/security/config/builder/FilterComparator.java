package com.devdive.backend.security.config.builder;

import com.devdive.backend.security.filter.*;
import jakarta.servlet.Filter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FilterComparator implements Comparator<Filter> {

    private final Map<String,Integer> filterToOrder=new HashMap<>();
    private static final int INITIAL_ORDER = 100;
    private static final int ORDER_STEP = 100;

    public FilterComparator() {
        Step step = new Step(INITIAL_ORDER, ORDER_STEP);
        filterToOrder.put(SecurityContextPersistenceFilter.class.getName(),step.next());
        filterToOrder.put(AccessTokenValidationAndRevocationFilter.class.getName(),step.next());
        filterToOrder.put(CorsFilter.class.getName(),step.next());
        filterToOrder.put(TokenAuthenticationFilter.class.getName(),step.next());
        filterToOrder.put(AnonymousAuthenticationFilter.class.getName(),step.next());
        filterToOrder.put(ExceptionTranslationFilter.class.getName(),step.next());
        filterToOrder.put(FilterSecurityInterceptor.class.getName(),step.next());
    }

    @Override
    public int compare(Filter o1, Filter o2) {
        Integer left = filterToOrder.get(o1.getClass().getName());
        Integer right = filterToOrder.get(o2.getClass().getName());
        return left-right;
    }

    private static class Step {

        private int value;
        private final int stepSize;

        Step(int initialValue, int stepSize) {
            this.value = initialValue;
            this.stepSize = stepSize;
        }

        int next() {
            int value = this.value;
            this.value += this.stepSize;
            return value;
        }
    }
}
