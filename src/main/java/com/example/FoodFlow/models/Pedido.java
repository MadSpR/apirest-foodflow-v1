package com.example.FoodFlow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DetallePedido> detallePedido;

    //@Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido", nullable = false, updatable = false)
    private LocalDateTime fecha_pedido;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido = EstadoPedido.PENDIENTE; //estado del pedido por defecto

    private BigDecimal total;

    public Pedido() {
    }

    public Pedido(Usuario usuario, List<DetallePedido> detallePedido, LocalDateTime fecha_pedido, EstadoPedido estadoPedido, BigDecimal total) {
        this.usuario = usuario;
        this.detallePedido = detallePedido;
        this.fecha_pedido = fecha_pedido;
        this.estadoPedido = estadoPedido;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
