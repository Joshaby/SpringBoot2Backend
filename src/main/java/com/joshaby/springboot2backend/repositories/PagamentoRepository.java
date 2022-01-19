package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
