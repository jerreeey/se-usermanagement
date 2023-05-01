package com.example.userservice.repository;

import com.example.userservice.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

    Users findUserByUserId(@Param("userId") String userId);

    Optional<Users> findByEmail(@Param("email") String email);

}
