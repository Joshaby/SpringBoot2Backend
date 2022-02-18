package com.joshaby.springboot2backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.endereco = endereco;
    }

    public Double getTotalPedido() {
        return itens.stream().reduce(0.0, (resultado, item) -> resultado + item.getSubtotal(), Double::sum);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder pedidoString = new StringBuilder();
        pedidoString.append(String.format("Número: %d\n", getId()));
        pedidoString.append(String.format("Instante: %s\n", dateFormat.format(getInstante())));
        pedidoString.append(String.format("Cliente: %s\n", getCliente().getNome()));
        pedidoString.append(String.format("Situação: %s\n", getPagamento().getEstadoPagamento().getDescricao()));
        pedidoString.append("Detalhes:\n");
        for (ItemPedido item : itens) {
            pedidoString.append(item.toString()).append("\n");
        }
        pedidoString.append(String.format("Valor total: %s\n", numberFormat.format(getTotalPedido())));
        return pedidoString.toString();
    }
}
