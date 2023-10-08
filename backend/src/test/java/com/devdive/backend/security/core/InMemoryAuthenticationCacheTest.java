package com.devdive.backend.security.core;

import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.core.cache.InMemoryAuthenticationCache;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InMemoryAuthenticationCacheTest {
    AuthenticationCache inMemoryAuthenticationCache = InMemoryAuthenticationCache.getInstance();

    @Test
    void findAuthentication() {
        String mail = "test@mail.com";
        Authentication authentication = mock(Authentication.class);
        inMemoryAuthenticationCache.addAuthentication(mail, authentication);
        Authentication findAuthentication = inMemoryAuthenticationCache.findAuthentication(mail);

        assertThat(findAuthentication).isEqualTo(authentication);
    }

    @Test
    void addAuthentication() {
        String mail = "test@mail.com";
        Authentication authentication1 = mock(Authentication.class);
        Authentication authentication2 = mock(Authentication.class);
        Authentication authentication3 = mock(Authentication.class);
        inMemoryAuthenticationCache.addAuthentication(mail, authentication1);
        inMemoryAuthenticationCache.addAuthentication(mail, authentication2);
        inMemoryAuthenticationCache.addAuthentication(mail, authentication3);
        Authentication findAuthentication = inMemoryAuthenticationCache.findAuthentication(mail);

        assertThat(findAuthentication).isEqualTo(authentication3);
    }
}
