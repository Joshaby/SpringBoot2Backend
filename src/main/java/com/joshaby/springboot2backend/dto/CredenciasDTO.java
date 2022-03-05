package com.joshaby.springboot2backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredenciasDTO {

    private String email;

    private String senha;
}
