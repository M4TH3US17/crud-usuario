package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<User> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User findUserById(Long id) {
        return repository.findById(id).get();
    }

    public User save(UserDTO userDTO) {
        return repository.save(new User(null, userDTO.getLogin(), userDTO.getPassword()));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, UserDTO userDTO) {
        User entity = repository.findById(id).get();
        updateData(entity, userDTO);
        return repository.save(entity);
    }

    private void updateData(User entity, UserDTO dto){
        entity      .setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
    }
}
