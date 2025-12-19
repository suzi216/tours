package com.discoveralbania.tours.services;

import com.discoveralbania.tours.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;

@Component
@Setter
@Getter
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String SECRET_KEY = "my-super-secure-jwt-secret-key-32-chars-min";
    private final long EXPIRATION_TIME = 86400000; // 24 hours
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateTestAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", "ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}