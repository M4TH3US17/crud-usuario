package com.example.demo.security.jwt;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class TokenDTO implements Serializable {

    private String token;

}
