package com.example.FoodFlow.controllers;

import com.example.FoodFlow.DTOs.SolicitudPedidoDTO;
import com.example.FoodFlow.models.Pedido;
import com.example.FoodFlow.models.Usuario;
import com.example.FoodFlow.repositories.UsuarioRepo;
import com.example.FoodFlow.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/all")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> addNewPedido(@RequestBody SolicitudPedidoDTO solicitudPedidoDTO){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepo.findByNombre(auth.getName()); //nombre dado por el token JWT

            if(usuario == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Pedido newPedido = pedidoService.crearPedido(solicitudPedidoDTO, usuario.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<Pedido>> getPedidoByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepo.findByNombre(auth.getName()); //nombre dado por el token JWT

        if(usuario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Pedido> pedidos = pedidoService.getPedidoByUser(usuario.getId());
        if(pedidos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable Long id, @RequestParam String newEstado){
        try{
            Pedido updatedPedido = pedidoService.updateEstadoPedido(id, newEstado);
            return ResponseEntity.ok(updatedPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") //TODO: cambiar para que lo marque como borrado y lo oculte en lugar de eliminarlo del todo
    public ResponseEntity<Map<String,String>> deletePedido (@PathVariable Long id){
        try{
            pedidoService.deletePedido(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Pedido eliminado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
