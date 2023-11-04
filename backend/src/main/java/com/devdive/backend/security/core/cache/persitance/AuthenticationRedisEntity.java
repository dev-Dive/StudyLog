package com.devdive.backend.security.core.cache.persitance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("authentication")
public class AuthenticationRedisEntity {


    @Id
    private String mail;
    private Long id;
    private String type;
    private String authentication;
}
