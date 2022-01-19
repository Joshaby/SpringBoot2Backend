package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
