package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CrudUsuarioApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsuarioApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "PUT", "PATCH", "POST",  "OPTIONS", "DELETE", "HEAD", "TRACE", "CONNECT");
	}
}
