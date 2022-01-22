package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Categoria;
import com.joshaby.springboot2backend.entities.Produto;
import com.joshaby.springboot2backend.repositories.CategoriaRepository;
import com.joshaby.springboot2backend.repositories.ProdutoRepository;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.orElseThrow(
                () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Produto.class.getName())));
    }

    public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
            String nome, List<Integer> categoriasId, Pageable pageable) {

        List<Categoria> categorias =  categoriaRepository.findAllById(categoriasId);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageable);
    }
}
