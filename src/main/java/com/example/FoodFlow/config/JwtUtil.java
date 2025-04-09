package com.example.FoodFlow.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

// genera y valida tokens JWT
// expira en 1 día
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "fS4ELIb7CX5JDk5AJM8h0nR9vWNRuYI2ytDzv+RE5q9VE6pOMbOYhLO5rRgjLDZGy15hz53HFQoTGzDhOIR4Sw==";
    private static final long EXPIRATION_TIME = 86400000; //1 día en milisegundos

    //Algoritmo de firma HMAC SHA-512
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS512;

    //clave secreta decodificada
    private static final SecretKey KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
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
    private boolean validateToken(String token, String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}