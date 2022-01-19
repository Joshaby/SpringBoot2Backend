package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
