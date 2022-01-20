package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.joshaby.springboot2backend.entities.enums.EstadoPagamentoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer estadoPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    @Getter(onMethod = @__(@JsonIgnore))
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamentoEnum estadoPagemento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = (estadoPagemento == null) ? null : estadoPagemento.getTipo();
        this.pedido = pedido;
    }
}
