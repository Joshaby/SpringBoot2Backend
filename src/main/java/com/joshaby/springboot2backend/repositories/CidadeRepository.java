package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
