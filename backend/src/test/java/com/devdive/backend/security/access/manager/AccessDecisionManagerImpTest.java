package com.devdive.backend.security.access.manager;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.authentication.Authentication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AccessDecisionManagerImpTest {

    @Test
    @DisplayName("권한 인증 성공")
    void authorizeSuccess() throws AccessDeniedException {

        AccessDecisionManagerImp accessDecisionManager =
                new AccessDecisionManagerImp(List.of((authentication, roles) -> {
                    String credentials = authentication.getAuthorities();
                    return credentials.equals(roles.get(0))?AccessDecisionVoter.ACCESS_GRANTED:AccessDecisionVoter.ACCESS_DENIED;
                }));

        accessDecisionManager.decide(new Authentication() {
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
                return "ADMIN";
            }
        },List.of("ADMIN"));
    }

    @Test
    @DisplayName("권한 인증 실패")
    void authorizeFail() {

        AccessDecisionManagerImp accessDecisionManager =
                new AccessDecisionManagerImp(List.of((authentication, roles) -> {
                    String credentials = authentication.getAuthorities();
                    return credentials.equals(roles.get(0))?AccessDecisionVoter.ACCESS_GRANTED:AccessDecisionVoter.ACCESS_DENIED;
                }));

        Authentication authentication = new Authentication() {
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
        };

        assertThatThrownBy(()->accessDecisionManager.decide(authentication,List.of("ADMIN")))
                .isInstanceOf(AccessDeniedException.class);
    }
}
