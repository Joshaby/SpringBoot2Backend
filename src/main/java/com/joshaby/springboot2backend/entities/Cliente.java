package com.joshaby.springboot2backend.entities;

import com.joshaby.springboot2backend.entities.enums.TipoClienteEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String cpfOuCnpj;

    private Integer tipoCliente;

    private String senha;

    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "tb_telefones")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoClienteEnum tipoCliente, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getTipo();
        this.senha = senha;
    }
}
