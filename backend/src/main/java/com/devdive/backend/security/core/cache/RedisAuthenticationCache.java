package com.devdive.backend.security.core.cache;

import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.authentication.token.AnonymousAuthenticationToken;
import com.devdive.backend.security.authentication.token.JwtTokenAuthenticationToken;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.cache.persitance.AuthenticationRedisEntity;
import com.devdive.backend.security.core.cache.persitance.AuthenticationRedisRepo;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class RedisAuthenticationCache implements AuthenticationCache {

    private final AuthenticationRedisRepo repo;
    private static AuthenticationCache instance;

    private RedisAuthenticationCache(AuthenticationRedisRepo repo) {
        this.repo = repo;
    }

    public static AuthenticationCache getInstance() {
        if (instance == null) {
            throw new NullPointerException("초기화 하고 사용해주세요");
        }

        return instance;
    }

    public static void init(AuthenticationRedisRepo repo) {
        if (instance == null) {
            synchronized (RedisAuthenticationCache.class) {
                if (instance == null) {
                    instance = new RedisAuthenticationCache(repo);
                }
            }
        }
    }

    @Override
    public Authentication findAuthentication(String email) {
        Optional<AuthenticationRedisEntity> item = repo.findById(email);
        if (item.isEmpty()) {
            return null;
        }

        AuthenticationRedisEntity authenticationRedisEntity = item.get();
        JwtTokenAuthenticationToken jwtTokenAuthenticationToken = new JwtTokenAuthenticationToken(new User(authenticationRedisEntity.getMail(),
                authenticationRedisEntity.getId()));
        jwtTokenAuthenticationToken.setAuthenticated(authenticationRedisEntity.getAuthentication());
        return jwtTokenAuthenticationToken;
    }

    @Override
    public void addAuthentication(String key, Authentication authentication) {
        String type = authentication.getClass().getSimpleName();

        if (type != AnonymousAuthenticationToken.class.getSimpleName()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            repo.save(new AuthenticationRedisEntity(key, userDetails.getId(), type,authentication.getAuthorities()));
        }
    }

    @Override
    public void remove(String key) {
        repo.deleteById(key);
    }

    @Override
    public void removeAll() {
        repo.deleteAll();
    }
}
