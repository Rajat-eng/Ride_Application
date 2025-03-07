package com.rajat.uber.security;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rajat.uber.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10)) // 10mins
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L*60*60*24*7)) // 7 day 
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        // we have bypassed filtrechain , threfore I am in spring context not security context 
        // If token is expired it throws error in controller
        // handle this in GlobalException class
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSecretKey()) // Use setSigningKey() instead of verifyWith()
            .build()
            .parseClaimsJws(token)
            .getBody(); 

    return Long.valueOf(claims.getSubject());

    }
}
