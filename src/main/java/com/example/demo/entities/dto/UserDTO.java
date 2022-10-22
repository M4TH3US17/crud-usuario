package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor @Data
public class UserDTO implements Serializable {

    @NotBlank(message = "")
    private String username;
    @NotBlank(message = "")
    private String password;
}
