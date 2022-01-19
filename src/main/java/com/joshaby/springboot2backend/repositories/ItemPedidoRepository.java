package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.ItemPedido;
import com.joshaby.springboot2backend.entities.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
