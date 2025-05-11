package com.example.FoodFlow.DTOs;


import java.util.List;

public class SolicitudPedidoDTO {

    private List<ItemPedidoDTO> items; //Lista de platos con las cantidades de cada uno

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}
