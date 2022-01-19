package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.joshaby.springboot2backend.entities.enums.EstadoPagamentoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamentos_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

    public PagamentoComCartao(
            Integer id, EstadoPagamentoEnum estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {

        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
