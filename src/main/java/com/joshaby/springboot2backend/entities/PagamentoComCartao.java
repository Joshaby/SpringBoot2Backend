package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.joshaby.springboot2backend.entities.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamentos_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

    public PagamentoComCartao(
            Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {

        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
