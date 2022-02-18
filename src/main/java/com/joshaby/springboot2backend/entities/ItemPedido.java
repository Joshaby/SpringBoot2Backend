package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;

    private Integer quantidade;

    private Double preco;

    public ItemPedido(Produto produto, Pedido pedido, Double desconto, Integer quantidade, Double preco) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Double getSubtotal() {
        return (getPreco() - getDesconto()) * getQuantidade();
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder itemString = new StringBuilder();
        itemString.append(String.format("Produto: %s\n", id.getProduto().getNome()));
        itemString.append(String.format("Qtde: %d\n", getQuantidade()));
        itemString.append(String.format("Preço unitário: %s\n", numberFormat.format(getPreco())));
        itemString.append(String.format("Subtotal: %s\n", numberFormat.format(getSubtotal())));
        return itemString.toString();
    }
}
