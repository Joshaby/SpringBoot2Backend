package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByEmail(String email);
}
