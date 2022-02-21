package com.joshaby.springboot2backend.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer tipo;
    private String descricao;

    public static Perfil toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        else {
            Optional<Perfil> perfil = Arrays.stream(Perfil.values()).filter(p -> p.getTipo().equals(tipo)).findFirst();
            return perfil.orElseThrow(() -> new IllegalArgumentException(String.format("Id %d inválido", tipo)));
        }
    }
}
