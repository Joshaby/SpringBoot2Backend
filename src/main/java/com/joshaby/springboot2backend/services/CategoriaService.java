package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Categoria;
import com.joshaby.springboot2backend.repositories.CategoriaRepository;
import com.joshaby.springboot2backend.services.exceptions.DataIntegrityException;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(
                () -> new ObjectNotFoundException(
                        String.format("Objeto %d não encontrado! Tipo: %s", id, Categoria.class.getName())));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId());
        return repository.save(categoria);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(String.format("Não é possível deletat a categoria, ela possuí produtos!"));
        }
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
