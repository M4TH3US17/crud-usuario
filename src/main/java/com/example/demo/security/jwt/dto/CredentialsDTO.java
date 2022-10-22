package com.example.demo.security.jwt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @NoArgsConstructor
public class CredentialsDTO implements Serializable {

    @NotBlank(message = "{credentials.username.not.blank}")
    private String username;

    @NotBlank(message = "{credentials.password.not.blank}")
    private String password;

}