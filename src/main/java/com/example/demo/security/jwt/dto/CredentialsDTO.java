package com.example.demo.security.jwt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @NoArgsConstructor
public class CredentialsDTO implements Serializable {

    @NotBlank(message = "")
    private String username;
    @NotBlank(message = "")
    private String password;

}