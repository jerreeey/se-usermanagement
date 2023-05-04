package com.example.userservice.controller;

import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userservice.controller.security.auth.UserUpdateRequest;

@RestController
@RequestMapping("/users")
public class UsersRestController {

    @Autowired
    UsersService usersService;

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        try {
            return ResponseEntity.ok(usersService.updateUserByUserId(userId, request.getEmail(), request.getPassword()));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        try {
            usersService.deleteUserByUserId(userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
