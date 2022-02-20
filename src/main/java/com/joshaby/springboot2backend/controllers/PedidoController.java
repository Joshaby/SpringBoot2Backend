package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.entities.Pedido;
import com.joshaby.springboot2backend.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    public PedidoService service;

    @GetMapping(value = "/{id}")
    public Pedido find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
        pedido = service.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
