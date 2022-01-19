package com.joshaby.springboot2backend.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TipoClienteEnum {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer tipo;
    private String descricao;

    public static TipoClienteEnum toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        Optional<TipoClienteEnum> optional = Arrays.stream(TipoClienteEnum.values()).filter(
                t -> t.getTipo().equals(tipo)).findFirst();

        return optional.orElseThrow(() -> new IllegalArgumentException(String.format("Id %s inválido", tipo)));
    }
}


