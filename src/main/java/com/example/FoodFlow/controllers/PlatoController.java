package com.example.FoodFlow.controllers;

import com.example.FoodFlow.models.Plato;
import com.example.FoodFlow.repositories.PlatoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/menu")
public class PlatoController {

    @Autowired
    private PlatoRepo platoRepo;

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

    @PostMapping(path = "/all")
    public @ResponseBody Iterable<Plato> getAllPlatos(){
        return platoRepo.findAll();
    }

}
