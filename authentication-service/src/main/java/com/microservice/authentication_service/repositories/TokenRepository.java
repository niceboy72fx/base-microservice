package com.microservice.authentication_service.repositories;

import com.microservice.authentication_service.model.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<RedisToken, String> {
}
