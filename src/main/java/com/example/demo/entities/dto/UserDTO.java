package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor @Data
public class UserDTO implements Serializable {

    @NotBlank(message = "{user.username.not.blank}")
    private String username;

    @NotBlank(message = "{user.password.not.blank}")
    private String password;

    @NotBlank(message = "{user.contact.not.blank}")
    private String contact;

    @NotNull(message = "{user.age.not.null}")
    private Integer age;
}
