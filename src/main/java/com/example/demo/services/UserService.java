package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.*;
import com.example.demo.entities.enums.RoleUser;
import com.example.demo.exeptions.notfound.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.security.jwt.*;
import com.example.demo.security.jwt.dto.CredentialsDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.security.jwt.TokenDTO;

@Slf4j
@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository         repository;
    private final BCryptPasswordEncoder  passwordEncoder;
    private final UserDetailsServiceImpl userDetailsImpl;
    private final JWTService             Jwt;

    public Page<ResponseUserDTO> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable).map(user -> new ResponseUserDTO(user));
    }

    public ResponseUserDTO findUserById(Long id) throws Exception {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário Não Encntrado!"));
        return new ResponseUserDTO(user);
    }

    public ResponseUserDTO save(UserDTO userDTO) {
        User user = new User();
        updateData(user, userDTO);
        return new ResponseUserDTO(repository.save(user));
    }

    public void delete(Long id) throws Exception {
        if(repository.existsById(id) == false) throw new UserNotFoundException("Usuário Não Encntrado!");
        repository.deleteById(id);
    }

    public ResponseUserDTO update(Long id, UserDTO userDTO) throws Exception {
        User entity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário Não Encntrado!"));
        updateData(entity, userDTO);
        return new ResponseUserDTO(repository.save(entity));
    }

    public TokenDTO login (CredentialsDTO userDTO) {
        UserDetails user = userDetailsImpl.loadUserByUsername(userDTO.getUsername());
        boolean senhaEhValida = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());

        try {
            if (senhaEhValida) {
                User usuarioDoBanco = repository.findUserByUsername(user.getUsername());
                return new TokenDTO(Jwt.generationToken(usuarioDoBanco));
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username ou Senha Inválidos!");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Inválida!");
    }

    private void updateData(User entity, UserDTO dto){
        if(entity.getRole() == null) entity.setRole(RoleUser.ROLE_USER);

        if(dto.getUsername() != null) entity.setUsername(dto.getUsername());
        if(dto.getAge()      != null) entity.setAge(dto.getAge());
        if(dto.getContact()  != null) entity.setContact(dto.getContact());
        if(dto.getPassword() != null) entity.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
