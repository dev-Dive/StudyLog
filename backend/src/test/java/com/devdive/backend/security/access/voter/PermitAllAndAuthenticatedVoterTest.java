package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.core.Authentication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PermitAllAndAuthenticatedVoterTest {

    @Test
    @DisplayName("permitAll 통과 테스트")
    void permitAllPassTest() {
        PermitAllAndAuthenticatedVoter permitAllAndAuthenticatedVoter = new PermitAllAndAuthenticatedVoter();
        int result = permitAllAndAuthenticatedVoter.vote(new Authentication() {
            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public String getAuthorities() {
                return "USER";
            }
        }, List.of("permitAll"));

        assertThat(result).isEqualTo(AccessDecisionVoter.ACCESS_GRANTED);
    }

    @Test
    @DisplayName("authenticated권한 실패 테스트")
    void authenticatedNonPassTest() {
        PermitAllAndAuthenticatedVoter permitAllAndAuthenticatedVoter = new PermitAllAndAuthenticatedVoter();
        int result = permitAllAndAuthenticatedVoter.vote(new Authentication() {
            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public String getAuthorities() {
                return "ANONYMOUS";
            }
        }, List.of("authenticated"));

        assertThat(result).isEqualTo(AccessDecisionVoter.ACCESS_DENIED);
    }

    @Test
    @DisplayName("authenticated권한 성공 테스트")
    void authenticatedPassTest() {
        PermitAllAndAuthenticatedVoter permitAllAndAuthenticatedVoter = new PermitAllAndAuthenticatedVoter();
        int result = permitAllAndAuthenticatedVoter.vote(new Authentication() {
            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public String getAuthorities() {
                return "user";
            }
        }, List.of("authenticated"));

        assertThat(result).isEqualTo(AccessDecisionVoter.ACCESS_GRANTED);
    }
}