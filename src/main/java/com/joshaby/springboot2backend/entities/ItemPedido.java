package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

    @EmbeddedId
    private ItemPedidoPK id;

    private Double desconto;

    private Integer quantidade;

    private Double preco;

}
