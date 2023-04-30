package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.mongodb.repository.*;
public interface UserRepository extends MongoRepository<User, Integer> {
}