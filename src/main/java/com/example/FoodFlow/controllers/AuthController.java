package com.example.FoodFlow.controllers;

import com.example.FoodFlow.DTOs.AuthRequestDTO;
import com.example.FoodFlow.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


// Ruta para iniciar sesión POST /auth/login
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // devuelve un jwt si las credenciales son correctas
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO){
        String token = authService.login(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }
}
