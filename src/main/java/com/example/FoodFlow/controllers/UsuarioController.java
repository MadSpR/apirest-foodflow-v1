package com.example.FoodFlow.controllers;

import com.example.FoodFlow.models.Rol;
import com.example.FoodFlow.models.Usuario;
import com.example.FoodFlow.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping(path = "/user") //URL starts with /user (after app path)
public class UsuarioController { //TODO: mover la lógica de negocio a UsuarioService (crear DTO?)
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String nombre, @RequestParam String email, @RequestParam String pass, @RequestParam String direccion, @RequestParam(defaultValue = "USER") String rol, Principal principal){
            //@ResponseBody means the returned String is the response, not a view name
            //@RequestParam means it is a parameter from the GET or POST request

        //comprueba si es un admin y tiene permisos para crear otro usuario administrador
        if (rol.equalsIgnoreCase("ADMIN")){
            if (principal == null){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permisos para crear un usuario administrador");
            }
            try{
                Usuario actual = usuarioRepo.findByNombre(principal.getName());

                if(!actual.getRol().name().equals("ADMIN")){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Solo un administrador puede realizar esta acción");
                }

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario no se ha encontrado o no tiene permisos");
            }
        }

        //crea el usuario
        Usuario usu = new Usuario();
            usu.setNombre(nombre);
            usu.setEmail(email);
            //pass = encryptPass(pass);
            usu.setPassword(passwordEncoder.encode(pass)); //codifica la pass
            usu.setDireccion(direccion);
            usu.setRol(Rol.valueOf(rol));

            usuarioRepo.save(usu);
            return "Saved\n";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Usuario> getAllUsers(){
        //this returns a JSON or XML with the users
        return usuarioRepo.findAll();
    }

}
