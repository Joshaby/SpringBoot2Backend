package com.joshaby.springboot2backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO {

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido!")
    private String email;
}
