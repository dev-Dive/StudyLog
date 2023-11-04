package com.devdive.backend.security.core.cache.config;

import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.cache.persitance.AuthenticationRedisRepo;
import com.devdive.backend.security.core.cache.InMemoryAuthenticationCache;
import com.devdive.backend.security.core.cache.RedisAuthenticationCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${security.cache:InMemoryAuthenticationCache}")
    private String cacheName;

    @Bean
    public AuthenticationCache authenticationCache(AuthenticationRedisRepo authenticationRedisRepo) {
        switch (cacheName) {
            case "InMemoryAuthenticationCache":
                return InMemoryAuthenticationCache.getInstance();
            case "RedisAuthenticationCache": {
                RedisAuthenticationCache.init(authenticationRedisRepo);
                return RedisAuthenticationCache.getInstance();
            }
            default:
                throw new IllegalArgumentException("잘못된 인자 입니다");
        }
    }
}
