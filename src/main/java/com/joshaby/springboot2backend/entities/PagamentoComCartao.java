package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamentos_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;
}
