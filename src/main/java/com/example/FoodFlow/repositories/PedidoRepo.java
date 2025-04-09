package com.example.FoodFlow.repositories;

import com.example.FoodFlow.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long userId);
    List<Pedido> findAll();
}
