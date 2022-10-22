package com.example.demo.security.jwt;

import com.example.demo.entities.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("jwt.auth.secret-key")
    private String secret;
    private final long expiration = 86400000;

    public String generationToken(User obj) {
        return Jwts.builder()
                .setSubject(obj.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims captureToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = captureToken(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) { return false; }
    }

    public String captureCliente(String token) throws ExpiredJwtException {
        return (String) captureToken(token).getSubject();
    }
}
