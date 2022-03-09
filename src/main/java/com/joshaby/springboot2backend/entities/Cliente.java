package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joshaby.springboot2backend.dto.ClienteDTO1;
import com.joshaby.springboot2backend.dto.ClienteDTO2;
import com.joshaby.springboot2backend.entities.enums.Perfil;
import com.joshaby.springboot2backend.entities.enums.TipoCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @JsonIgnore
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_perfis")
    private Set<Integer> perfis = new HashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Endereco> enderecos = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_telefones")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getTipo();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO1 dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.email = dto.getEmail();
    }

    public Cliente(ClienteDTO2 dto) {
        this.id = null;
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.cpfOuCnpj = dto.getCpfOuCnpj();
        this.tipoCliente = dto.getTipoCliente();
        this.senha = dto.getSenha();
        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
        this.enderecos.add(new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
                dto.getBairro(), dto.getCep(), this, cidade));
        telefones.add(dto.getTelefone1());
        if (dto.getTelefone2() != null) {
            telefones.add(dto.getTelefone2());
        }
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getTipo());
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }
}
