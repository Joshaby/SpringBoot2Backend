package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.controllers.utils.ControllersUtils;
import com.joshaby.springboot2backend.dto.ProdutoDTO;
import com.joshaby.springboot2backend.entities.Produto;
import com.joshaby.springboot2backend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public Produto find(@PathVariable Integer id) {
        return service.find(id);
    }

    @GetMapping(value = "/pages")
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
