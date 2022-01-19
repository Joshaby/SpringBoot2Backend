package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamentos_com_boleto")
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataVencimento;
}
