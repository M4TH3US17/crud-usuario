package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.*;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.security.jwt.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository         repository;
    private final ModelMapper            mapper;
    private final BCryptPasswordEncoder  passwordEncoder;
    private final UserDetailsServiceImpl userDetailsImpl;
    private final JWTService             Jwt;

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

    public RespostaDeLogin login (UserDTO userDTO) {
        UserDetails user = userDetailsImpl.loadUserByUsername(userDTO.getLogin());
        boolean senhaEhValida = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());

        try {
            if (senhaEhValida) {
                User usuarioDoBanco = repository.findUserByLogin(user.getUsername());
                return new RespostaDeLogin(Jwt.generationToken(usuarioDoBanco));
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username ou Senha Inválidos!");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Inválida!");
    }

    private void updateData(User entity, UserDTO dto){
        entity      .setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
    }
}
