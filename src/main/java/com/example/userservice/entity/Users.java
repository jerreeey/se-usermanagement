package com.example.userservice.entity;

import com.example.userservice.controller.security.auth.enumeration.Role;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;


import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {
    @Id
    private String userId;

    private String email;

    private String password;

    @Field("role")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
