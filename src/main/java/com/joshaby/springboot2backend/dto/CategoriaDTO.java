package com.joshaby.springboot2backend.dto;

import com.joshaby.springboot2backend.entities.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "O nome é obrigatório")
    @Length(min = 6, max = 80, message = "O tamanho do nome deve ser entre 6 e 80 caracteres")
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
