package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
