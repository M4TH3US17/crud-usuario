package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Entity @Table(name = "users") @Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include @Column(name = "id_user")
	private Long idUser;
	private String login;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
}
