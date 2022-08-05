package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.ClienteDTO1;
import com.joshaby.springboot2backend.dto.ClienteDTO2;
import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@Tag(description = "providencia um CRUD para Clientes", name = "Cliente Controller")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get Cliente",
            description = "Retorna um Cliente a partir de um ID",
            security = {@SecurityRequirement(name = "bearer-key")})
    public Cliente find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO2 dto) {
        Cliente cliente = service.insert(new Cliente(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO1 dto, @PathVariable Integer id) {
        dto.setId(id);
        service.update(new Cliente(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
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
    @GetMapping("/page")
    public Page<ClienteDTO1> findPage(Pageable pageable) {
        return service.findPage(pageable).map(ClienteDTO1::new);
    }
}
