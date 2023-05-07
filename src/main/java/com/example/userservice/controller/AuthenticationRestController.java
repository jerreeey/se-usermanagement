package com.example.userservice.controller;

import com.example.userservice.controller.security.auth.AuthenticationRequest;
import com.example.userservice.controller.security.auth.AuthenticationResponse;
import com.example.userservice.controller.security.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationRestController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody AuthenticationRequest request) {
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sessions")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
