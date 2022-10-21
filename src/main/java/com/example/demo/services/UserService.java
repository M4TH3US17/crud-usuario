package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(UserDTO userDTO) {
        return new User();
    }

}
