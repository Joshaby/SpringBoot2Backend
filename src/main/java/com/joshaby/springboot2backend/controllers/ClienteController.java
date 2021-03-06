package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.ClienteDTO1;
import com.joshaby.springboot2backend.dto.ClienteDTO2;
import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public Cliente find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO2 dto) {
        Cliente cliente = service.insert(new Cliente(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO1 dto, @PathVariable Integer id) {
        dto.setId(id);
        service.update(new Cliente(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<ClienteDTO1> findAll() {
        return service.findAll().stream().map(ClienteDTO1::new).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public Page<ClienteDTO1> findPage(Pageable pageable) {
        return service.findPage(pageable).map(ClienteDTO1::new);
    }
}
