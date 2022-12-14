package com.example.demo.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    RoleUser(String role){this.role = role;}

    public String getRole() { return role; }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
