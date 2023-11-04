package com.devdive.backend.security.core.cache.persitance;

import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRedisRepo extends CrudRepository<AuthenticationRedisEntity, String> {

}
