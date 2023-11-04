package com.devdive.backend.security.core;


import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.authentication.token.JwtTokenAuthenticationToken;
import com.devdive.backend.security.core.cache.persitance.AuthenticationRedisRepo;
import com.devdive.backend.security.core.cache.RedisAuthenticationCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RedisAuthenticationCacheTest {

    @Autowired
    private AuthenticationRedisRepo repo;
    private AuthenticationCache redisAuthenticationCache;


    @BeforeEach
    public void before() {
        RedisAuthenticationCache.init(repo);
        redisAuthenticationCache = RedisAuthenticationCache.getInstance();
        repo.deleteAll();
    }

    @Test
    void findAuthentication() {
        String mail = "test@mail.com";
        redisAuthenticationCache.addAuthentication(mail, new JwtTokenAuthenticationToken(new User(mail, 1L)));
        Authentication findAuthentication = redisAuthenticationCache.findAuthentication(mail);

        UserDetails principal = (UserDetails) findAuthentication.getPrincipal();
        assertThat(principal.getId()).isEqualTo(1L);
    }

    @Test
    void addAuthentication() {
        String mail = "test@mail.com";
        Authentication authentication1 = new JwtTokenAuthenticationToken(new User(mail, 1L));
        Authentication authentication2 = new JwtTokenAuthenticationToken(new User(mail, 2L));
        Authentication authentication3 = new JwtTokenAuthenticationToken(new User(mail, 3L));
        redisAuthenticationCache.addAuthentication(mail, authentication1);
        redisAuthenticationCache.addAuthentication(mail, authentication2);
        redisAuthenticationCache.addAuthentication(mail, authentication3);
        Authentication findAuthentication = redisAuthenticationCache.findAuthentication(mail);

        UserDetails principal = (UserDetails) findAuthentication.getPrincipal();
        assertThat(principal.getId()).isEqualTo(3L);
    }


}
