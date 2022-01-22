package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Pagamento;
import com.joshaby.springboot2backend.repositories.PagamentoRepository;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento find(Integer id) {
        Optional<Pagamento> pagamento = repository.findById(id);
        return pagamento.orElseThrow(
                () -> new ObjectNotFoundException(String.format("Objeto $d não encontrado! Tipo: %s", id, Pagamento.class.getName())));
    }
}
