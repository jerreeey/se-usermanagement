package com.example.userservice.service;

import com.example.userservice.DTO.UsersDTO;
import com.example.userservice.entity.Users;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private UsersDTO convertUserToUserDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserId(user.getUserId());
        usersDTO.setEmail(user.getEmail());
        return usersDTO;
    }

    public UsersDTO updateUserByUserId(String userId, Optional<String> email, Optional<String> password) throws UserNotFoundException {
        Users user = usersRepository.findUserByUserId(userId);
        if (user != null) {
            email.ifPresent(user::setEmail);
            password.ifPresent(s -> user.setPassword(passwordEncoder.encode(s)));
            Users savedUser = usersRepository.save(user);
            return convertUserToUserDTO(savedUser);
        }
        throw new UserNotFoundException("User with Id " + userId + " not found");
    }

    public void deleteUserByUserId(String userId) {
        Users user = usersRepository.findUserByUserId(userId);
        if (user != null) {
            usersRepository.delete(user);
        } else {
            throw new UserNotFoundException("User with Id " + userId + " not found");
        }
    }
}
