package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.controllers.utils.ControllersUtils;
import com.joshaby.springboot2backend.dto.ProdutoDTO;
import com.joshaby.springboot2backend.entities.Produto;
import com.joshaby.springboot2backend.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
@Tag(
        name = "Produto Controller",
        description = "Recuperação de Produtos"
)
public class ProdutoController {

    private ProdutoService service;

    @GetMapping("/{id}")
    @Operation(description = "Retorna um Produto a partir de um ID")
    public Produto find(@PathVariable Integer id) {
        return service.find(id);
    }

    @GetMapping("/page")
    @Operation(description = "Retorna um Page de Produtos")
    public Page<ProdutoDTO> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            Pageable pageable) {

        String newNome = ControllersUtils.paramDecoder(nome);
        List<Integer> categoriasId = ControllersUtils.paramToList(categorias);
        System.out.println(categorias);
        return service.findDistinctByNomeContainingAndCategoriasIn(newNome, categoriasId, pageable).map(ProdutoDTO::new);
    }
}
