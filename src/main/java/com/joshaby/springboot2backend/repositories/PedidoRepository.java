package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
