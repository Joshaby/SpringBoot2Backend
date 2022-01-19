package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
