package com.example.userservice.controller.security.auth;

import com.example.userservice.DTO.UsersDTO;
import com.example.userservice.controller.security.auth.enumeration.Role;
import com.example.userservice.controller.security.auth.enumeration.TokenType;
import com.example.userservice.controller.security.config.JwtService;
import com.example.userservice.entity.Tokens;
import com.example.userservice.entity.Users;
import com.example.userservice.repository.TokenRepository;
import com.example.userservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  UsersRepository usersRepository;
  @Autowired
  TokenRepository tokenRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  JwtService jwtService;

  public AuthenticationResponse register(AuthenticationRequest request) {
    Users user = new Users();
    user.setUserId(ObjectId.get().toString());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.ROLE_USER);
    Users savedUser = usersRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setAccessToken(jwtToken);
    authenticationResponse.setUserDTO(convertUserToUserDTO(savedUser));
    return authenticationResponse;
  }

  private UsersDTO convertUserToUserDTO(Users user) {
    UsersDTO usersDTO = new UsersDTO();
    usersDTO.setUserId(user.getUserId());
    usersDTO.setEmail(user.getEmail());
    return usersDTO;
  }


  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    Users user = usersRepository.findByEmail(request.getEmail()).orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(user, jwtToken);
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setAccessToken(jwtToken);
    authenticationResponse.setUserDTO(convertUserToUserDTO(user));
    return authenticationResponse;
  }

  private void saveUserToken(Users user, String jwtToken) {
    Tokens token = new Tokens();
    token.setUser(user);
    token.setToken(jwtToken);
    token.setTokenType(TokenType.BEARER);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }


}
