package com.example.FoodFlow.services;

import com.example.FoodFlow.models.Usuario;
import com.example.FoodFlow.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNombre(username);
        if (usuario == null){
            throw new RuntimeException("No encontrado nombre de usuario: " + username);
        }
        return usuario;
    }
}
