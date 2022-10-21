package com.example.demo.security.jwt;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class RespostaDeLogin implements Serializable {

    private String token;

}
