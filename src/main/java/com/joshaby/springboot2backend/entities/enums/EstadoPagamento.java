package com.joshaby.springboot2backend.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

    PEDENTE(1, "Pendene"),
    CANCELADO(2, "Cancelado"),
    QUITADO(3, "Quitado");

    private Integer tipo;
    private String descricao;

    public static EstadoPagamento toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }

        Optional<EstadoPagamento> estadoPagemento = Arrays.stream(EstadoPagamento.values()).filter(
                e -> e.getTipo().equals(tipo)).findFirst();

        return estadoPagemento.orElseThrow(() -> new IllegalArgumentException(String.format("Id %s inválido", tipo)));
    }
}