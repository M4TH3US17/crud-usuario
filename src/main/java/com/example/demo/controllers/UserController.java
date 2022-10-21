package com.example.demo.controllers;

import com.example.demo.entities.dto.*;
import com.example.demo.security.jwt.RespostaDeLogin;
import com.example.demo.services.UserService;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @CrossOrigin("*")
@RequestMapping(name = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ResponseUserDTO>> findAllUsers(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllUsers(pageable));
    }

    @GetMapping(value = "/{idUser}", produces = "application/json")
    public ResponseEntity<ResponseUserDTO> findUserById(@PathVariable("idUser") Long idUser) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findUserById(idUser));
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDTO));
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RespostaDeLogin> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(service.login(userDTO));
    }

    @PutMapping(value = "/update/{idUser}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable("idUser") Long idUser, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok().body(service.update(idUser, userDTO));
    }

    @DeleteMapping(value = "/{idUser}", produces = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable("idUser") Long idUser) {
        service.delete(idUser);
        return ResponseEntity.ok().build();
    }
}
