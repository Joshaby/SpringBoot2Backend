package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.CategoriaDTO;
import com.joshaby.springboot2backend.entities.Categoria;
import com.joshaby.springboot2backend.services.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public Categoria find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO dto) {
        Categoria categoria = service.insert(new Categoria(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable Integer id) {
        dto.setId(id);
        Categoria categoria = service.update(new Categoria(dto));
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CategoriaDTO> findAll() {
        return service.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/page")
    public Page<CategoriaDTO> findPage(Pageable pageable) {
        return service.findPage(pageable).map(CategoriaDTO::new);
    }
}
