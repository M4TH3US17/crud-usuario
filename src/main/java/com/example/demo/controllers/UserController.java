package com.example.demo.controllers;

import com.example.demo.entities.dto.ResponseUserDTO;
import com.example.demo.entities.dto.UserDTO;
import com.example.demo.security.jwt.TokenDTO;
import com.example.demo.security.jwt.dto.CredentialsDTO;
import com.example.demo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @CrossOrigin("*")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor @Api("Usuário WebServices")
public class UserController {

    private final UserService service;

    @ApiOperation("Retorna uma lista paginada de usuários.")
    @GetMapping(value="/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ResponseUserDTO>> findAllUsers(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllUsers(pageable));
    }

    @ApiOperation("Busca um usuário por id.")
    @GetMapping(value = "/find/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUserDTO> findUserById(@PathVariable("idUser") Long idUser) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.findUserById(idUser));
    }

    @ApiOperation("Cria um novo usuário.")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDTO));
    }

    @ApiOperation("Autentica usuário no sistema.")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid CredentialsDTO credentials) {
        return ResponseEntity.ok().body(service.login(credentials));
    }

    @ApiOperation("Atualiza um determinado usuário.")
    @PutMapping(value = "/update/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable("idUser") Long idUser, @RequestBody @Valid UserDTO userDTO) throws Exception {
        return ResponseEntity.ok().body(service.update(idUser, userDTO));
    }

    @ApiOperation("Deleta um usuário por id.")
    @DeleteMapping(value = "/delete/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("idUser") Long idUser) throws Exception {
        service.delete(idUser);
        return ResponseEntity.ok().build();
    }
}
