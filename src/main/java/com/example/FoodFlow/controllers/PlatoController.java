package com.example.FoodFlow.controllers;

import com.example.FoodFlow.models.Plato;
import com.example.FoodFlow.repositories.PlatoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/menu")
public class PlatoController {
//TODO: añadir en lla bbdd una fila para id del restaurante al que pertenece y crear funcion para listar platos por restauranteID
    @Autowired
    private PlatoRepo platoRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add")
    public @ResponseBody String addNewPlato (@RequestParam String nombre, @RequestParam String descripcion, @RequestParam BigDecimal precio, @RequestParam byte[] imagen, @RequestParam Boolean disponible){



        Plato plato = new Plato();
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setPrecio(precio);
        plato.setImagen(imagen); //TODO: si el valor imagen viene nulo, guardar una imagen por defecto.
        plato.setDisponible(disponible);

        platoRepo.save(plato);
        return "Saved\n";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Plato> getAllPlatos(){
        return platoRepo.findAll();
    }

}
