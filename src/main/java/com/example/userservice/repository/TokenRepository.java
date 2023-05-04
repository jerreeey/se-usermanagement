package com.example.userservice.repository;

import com.example.userservice.entity.Tokens;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Tokens, Integer> {

}
