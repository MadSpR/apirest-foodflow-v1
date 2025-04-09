package com.example.FoodFlow.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detallePedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad; // cantidad de cada plato

    private BigDecimal precio_unitario;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "plato_id", referencedColumnName = "id")
    private Plato plato;

    public DetallePedido(Long id, int cantidad, BigDecimal precio_unitario) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public DetallePedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}
