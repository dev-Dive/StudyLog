package com.devdive.backend.security.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SecurityContextHolderStrategyImpTest {

    private final SecurityContextHolderStrategy strategy = new SecurityContextHolderStrategyImp();

    @BeforeEach
    void before() {
        strategy.clearContext();
    }

    @Test
    void getContext() {
        SecurityContextImp securityContextImp = new SecurityContextImp();
        strategy.setContext(securityContextImp);
        assertThat(strategy.getContext()).isEqualTo(securityContextImp);
    }

    @Test
    void setContext() {
        SecurityContextImp securityContextImp = new SecurityContextImp();
        assertThat(strategy.getContext()).isNotEqualTo(securityContextImp);
        strategy.setContext(securityContextImp);
        assertThat(strategy.getContext()).isEqualTo(securityContextImp);
    }

    @Test
    void createEmptyContext() {
        assertThat(strategy.createEmptyContext()).isNotNull();
    }

    @Test
    void clearContext() {
        SecurityContextImp securityContext = new SecurityContextImp();
        strategy.setContext(securityContext);

        strategy.clearContext();
        assertThat(strategy.getContext()).isNotEqualTo(securityContext);
    }
}
