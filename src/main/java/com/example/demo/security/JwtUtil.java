package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    

    // Secret key to sign the token (minimum 32 characters)
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Token expires in 10 hours - adjust for production
    private final long EXPIRATION = 1000 * 60 * 60 * 10; 
    
    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generates the token with the email as subject
    public String generateToken(String email) {
        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getKey())
            .compact();
    }

    // Extracts the email from the token 
    public String extractEmail(String token) {
        return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    // Validates the token
    public boolean validateToken(String token) {
        try {
                Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
