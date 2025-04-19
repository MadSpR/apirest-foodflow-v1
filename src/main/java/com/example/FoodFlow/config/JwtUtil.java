package com.example.FoodFlow.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

// genera y valida tokens JWT
// expira en 1 día
@Component
public class JwtUtil {

    private SecretKey KEY;
    private long EXPIRATION_TIME;

    //Algoritmo de firma HMAC SHA-512
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS512;

    public JwtUtil(@Value("${jwt.secret}") String SECRET_KEY,@Value("${jwt.expiration}") long EXPIRATION_TIME) {
        this.KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        this.EXPIRATION_TIME = EXPIRATION_TIME;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, ALGORITHM) // firma con la clave y algoritmo especificados
                .compact();
    }

    /**
     * Verifica si el token ha expirado
     */
    private boolean isTokenExpired(String token){
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    /**
     * Extrae el nombre de ususario del token JWT proporcionado
     */
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Valida si el token JWT es válido y pertenece al usuario proporcionado
     */
    public boolean validateToken(String token){
        return (extractUsername(token) != null && !isTokenExpired(token));
    }
}