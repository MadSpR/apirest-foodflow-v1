package com.example.FoodFlow.repositories;

import com.example.FoodFlow.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
