package com.example.userservice.entity;

import com.example.userservice.controller.security.auth.enumeration.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class Tokens {

  @Id
  public String id;

  @Indexed
  @Field
  public String token;

  @Field
  public TokenType tokenType = TokenType.BEARER;

  @Field
  public boolean revoked;

  @Field
  public boolean expired;

  @DBRef(lazy = true)
  public Users user;
}