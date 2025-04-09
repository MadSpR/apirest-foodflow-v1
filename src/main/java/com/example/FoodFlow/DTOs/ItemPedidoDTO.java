package com.example.FoodFlow.DTOs;

public class ItemPedidoDTO {

    private Long platoId;
    private int cantidad;

    public Long getPlatoId() {
        return platoId;
    }

    public void setPlatoId(Long platoId) {
        this.platoId = platoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
