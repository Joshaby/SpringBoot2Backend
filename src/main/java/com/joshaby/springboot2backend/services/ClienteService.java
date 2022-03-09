package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.entities.enums.Perfil;
import com.joshaby.springboot2backend.repositories.ClienteRepository;
import com.joshaby.springboot2backend.repositories.EnderecoRepository;
import com.joshaby.springboot2backend.security.User;
import com.joshaby.springboot2backend.services.exceptions.AuthorizationException;
import com.joshaby.springboot2backend.services.exceptions.DataIntegrityException;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UserService userService;

    public Cliente find(Integer id) {
        User user = userService.getUserAuthenticated();
        if ((user == null || !user.hasHole(Perfil.ADMIN)) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Cliente.class.getName())));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        find(cliente.getId());
        Cliente newCliente = repository.findById(cliente.getId()).get();
        updateCliente(newCliente, cliente);
        return repository.save(newCliente);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível remover o cliente, ele possuí pedidos!");
        }
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private void updateCliente(Cliente cliente, Cliente anotherCliente) {
        cliente.setNome(anotherCliente.getNome());
        cliente.setEmail(anotherCliente.getEmail());
    }
}
