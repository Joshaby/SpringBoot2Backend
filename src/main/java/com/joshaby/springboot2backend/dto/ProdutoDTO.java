package com.joshaby.springboot2backend.dto;

import com.joshaby.springboot2backend.entities.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private Integer id;

    private String nome;

    private Double preco;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
