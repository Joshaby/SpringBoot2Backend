package com.joshaby.springboot2backend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produtos")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Double preco;

    @ManyToMany(mappedBy = "produtos")
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itemPedidos = new HashSet<>();
}
