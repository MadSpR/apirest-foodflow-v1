package com.example.FoodFlow.services;

import com.example.FoodFlow.config.JwtUtil;
import com.example.FoodFlow.models.Usuario;
import com.example.FoodFlow.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // deprecated, se implementa en customuserservice para evitar crear bucles con el userdetails service
//    public Usuario getUsuarioByUsername(String username){
//        Usuario usuario = usuarioRepo.findByNombre(username);
//        if (usuario == null){
//            throw new RuntimeException("No encontrado nombre de usuario: " + username);
//        }
//        return usuario;
//    }

    // autentica un usuario y genera un token JWT
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password)));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
