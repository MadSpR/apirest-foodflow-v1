package com.example.FoodFlow.DTOs;


import java.util.List;

public class SolicitudPedidoDTO {

    private Long usuarioId;
    private List<ItemPedidoDTO> items; //Lista de platos con las cantidades de cada uno

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}
