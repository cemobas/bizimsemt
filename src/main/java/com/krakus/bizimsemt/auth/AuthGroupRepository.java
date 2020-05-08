package com.krakus.bizimsemt.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthGroupRepository extends MongoRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}
