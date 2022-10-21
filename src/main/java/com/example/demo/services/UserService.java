package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.*;
import com.example.demo.repositories.UserRepository;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ModelMapper    mapper;

    public Page<ResponseUserDTO> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable).map(user -> mapper.map(user, ResponseUserDTO.class));
    }

    public ResponseUserDTO findUserById(Long id) {
        User user = repository.findById(id).get();
        return mapper.map(user, ResponseUserDTO.class);
    }

    public ResponseUserDTO save(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        return mapper.map(repository.save(user), ResponseUserDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ResponseUserDTO update(Long id, UserDTO userDTO) {
        User entity = repository.findById(id).get();
        updateData(entity, userDTO);
        return mapper.map(repository.save(entity), ResponseUserDTO.class);
    }

    private void updateData(User entity, UserDTO dto){
        entity      .setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
    }
}
