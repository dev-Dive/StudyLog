package com.devdive.backend.security.authentication;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnonymousAuthenticationTokenTest {

    AnonymousAuthenticationToken authenticationToken=new AnonymousAuthenticationToken("anonymousUser","ANONYMOUS");

    @Test
    void getCredentials() {
        assertThat((String) authenticationToken.getCredentials()).isEqualTo("");
    }

    @Test
    void getDetails() {
        assertThat(authenticationToken.getDetails()).isEqualTo(null);
    }

    @Test
    void getPrincipal() {
        assertThat(authenticationToken.getPrincipal()).isEqualTo("anonymousUser");
    }

    @Test
    void getAuthorities() {
        assertThat(authenticationToken.getAuthorities()).isEqualTo("ANONYMOUS");
    }
}