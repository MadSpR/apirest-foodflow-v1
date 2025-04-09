package com.example.FoodFlow.controllers;

import com.example.FoodFlow.DTOs.SolicitudPedidoDTO;
import com.example.FoodFlow.models.Pedido;
import com.example.FoodFlow.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/all")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Object> addNewPedido(@RequestBody SolicitudPedidoDTO solicitudPedidoDTO){
        try {
            Pedido newPedido = pedidoService.crearPedido(solicitudPedidoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pedido>> getPedidoByUser(@PathVariable Long userId){
        List<Pedido> pedidos = pedidoService.getPedidoByUser(userId);
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

    @DeleteMapping("/delete/{id}") //TODO:añadir autenticación para eliminar un pedido
    public ResponseEntity<Map<String,String>> deletePedido (@PathVariable Long id){
        try{
            pedidoService.deletePedido(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Pedido eliminado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
