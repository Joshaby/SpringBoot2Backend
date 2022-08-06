package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.entities.Pedido;
import com.joshaby.springboot2backend.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedido Controller", description = "providencia inserção e recuperação de Pedidos")
public class PedidoController {

    @Autowired
    public PedidoService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get Pedido", description = "Retorna um Pedido a partir de um ID")
    public Pedido find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    @Operation(summary = "Post Pedido", description = "Adiciona um Pedido")
    public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
        pedido = service.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/page")
    @Operation(
            summary = "Page Pedidos",
            description = "Retorna um Page de Pedido do Cliente logado - Necessita de autenticação por token JWT",
            security = @SecurityRequirement(name = "bearer-key"))
    public Page<Pedido> findPage(Pageable page) {
        return service.findPage(page);
    }
}
