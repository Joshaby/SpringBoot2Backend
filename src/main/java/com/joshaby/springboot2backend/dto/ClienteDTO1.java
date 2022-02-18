package com.joshaby.springboot2backend.dto;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.services.validation.ClienteUpdate;
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
@ClienteUpdate
public class ClienteDTO1 implements Serializable {

    private Integer id;

    @NotEmpty(message = "O nome é obrigatório!")
    @Length(min = 4, max = 80, message = "O tamanho do nome deve ser entre 4 e 80 caracteres!")
    private String nome;

    @NotEmpty(message = "O email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    public ClienteDTO1(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
