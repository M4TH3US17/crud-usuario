package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class UserDTO implements Serializable {

    @NotBlank(message = "")
    private String login;
    @NotBlank(message = "")
    private String password;

}
