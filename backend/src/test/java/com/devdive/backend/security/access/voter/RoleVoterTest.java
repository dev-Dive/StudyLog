package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.core.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RoleVoterTest {


    @Test
    @DisplayName("권한 인증 성공")
    void authorizeSuccess() {
        RoleVoter roleVoter = new RoleVoter();
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "USER";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }
        };

        Assertions.assertEquals(roleVoter.vote(authentication, List.of("USER","ADMIN")), RoleVoter.ACCESS_GRANTED);
    }

    @Test
    @DisplayName("권한 인증 실패")
    void authorizeFail() {
        RoleVoter roleVoter = new RoleVoter();
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "USER";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }
        };

        Assertions.assertEquals(roleVoter.vote(authentication, List.of("ADMIN")), RoleVoter.ACCESS_DENIED);
    }
}