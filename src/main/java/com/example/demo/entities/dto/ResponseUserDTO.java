package com.example.demo.entities.dto;

import com.example.demo.entities.User;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter
public class ResponseUserDTO implements Serializable {

    private Long   idUser;
    private String username;
    private String contact;
    private Integer age;

    public ResponseUserDTO(User user) {
        idUser   = user.getIdUser();
        username = user.getUsername();
        contact  = user.getContact();
        age      = user.getAge();
    }
}
