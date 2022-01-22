package com.joshaby.springboot2backend.repositories;

import com.joshaby.springboot2backend.entities.Categoria;
import com.joshaby.springboot2backend.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT DISTINCT produto FROM Produto produto " +
            "INNER JOIN produto.categorias categorias " +
            "WHERE produto.nome LIKE %:nome% AND categorias in :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIN(
            @Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageable);
}
