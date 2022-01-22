package com.joshaby.springboot2backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ClienteDTO2 implements Serializable {

    @NotEmpty(message = "O nome é obrigatório!")
    @Length(min = 4, max = 80, message = "O tamanho do nome deve ser entre 4 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "O email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpfOuCnpj;

    private Integer tipoCliente;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String senha;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String telefone1;

    private String telefone2;

    private Integer cidadeId;
}
