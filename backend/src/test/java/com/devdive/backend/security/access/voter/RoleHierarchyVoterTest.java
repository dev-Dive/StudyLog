package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.core.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RoleHierarchyVoterTest {
    private String hierarchyString="ADMIN>MANAGER>USER";

    @Test
    @DisplayName("권한 인증 성공")
    void authorizeSuccess() {
        AccessDecisionVoter roleVoter = new RoleHierarchyVoter(hierarchyString);
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "ADMIN";
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

        Assertions.assertEquals(roleVoter.vote(authentication, List.of("USER")), RoleVoter.ACCESS_GRANTED);
    }

    @Test
    @DisplayName("권한 인증 실패")
    void authorizeFail() {
        AccessDecisionVoter roleVoter = new RoleHierarchyVoter(hierarchyString);
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "MANAGER";
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

    @Test
    @DisplayName("사용자의 인증 권한이 존재하지 않음")
    void userAuthorizeRoleIsNonExistent() {
        AccessDecisionVoter roleVoter = new RoleHierarchyVoter(hierarchyString);
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "READ";
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

    @Test
    @DisplayName("해당 url의 인증 권한이 존재하지 않음")
    void urlAuthorizeRoleIsNonExistent() {
        AccessDecisionVoter roleVoter = new RoleHierarchyVoter(hierarchyString);
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "ADMIN";
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

        Assertions.assertEquals(roleVoter.vote(authentication, List.of("READ")), RoleVoter.ACCESS_DENIED);
    }

    @Test
    @DisplayName("유저와 url둘다 인증 권한이 존재하지 않음")
    void urlAndUserAuthorizeRoleIsNonExistent() {
        AccessDecisionVoter roleVoter = new RoleHierarchyVoter(hierarchyString);
        Authentication authentication=new Authentication() {
            @Override
            public Object getCredentials() {
                return "READ";
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

        Assertions.assertEquals(roleVoter.vote(authentication, List.of("READ")), RoleVoter.ACCESS_DENIED);
    }
}