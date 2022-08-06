package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.CategoriaDTO;
import com.joshaby.springboot2backend.entities.Categoria;
import com.joshaby.springboot2backend.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Tag(
        name = "Categoria Controller",
        description = "CRUD para Categorias"
)
public class CategoriaController {

    private CategoriaService service;

    @GetMapping("/{id}")
    @Operation(description = "Retorna uma Categoria a partir de um ID")
    public Categoria find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(
            description = "Insere uma Categoria - Necessita de autenticação por token JWT - Apenas usuários ADMIN " +
                    "têm autorização para esse endpoint",
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO dto) {
        Categoria categoria = service.insert(new Categoria(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(
            description = "Atualiza uma Categoria - Necessita de autenticação por token JWT - Apenas usuários ADMIN " +
                    "têm autorização para esse endpoint",
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable Integer id) {
        dto.setId(id);
        Categoria categoria = service.update(new Categoria(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(
            description = "Deleta uma Categoria - Necessita de autenticação - Apenas usuários ADMIN têm autorização " +
                    "para este endpoint",
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(description = "Retorna todas as categorias")
    public List<CategoriaDTO> findAll() {
        return service.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/page")
    @Operation(description = "Retorna um Page de Categorias")
    public Page<CategoriaDTO> findPage(Pageable pageable) {
        return service.findPage(pageable).map(CategoriaDTO::new);
    }
}
