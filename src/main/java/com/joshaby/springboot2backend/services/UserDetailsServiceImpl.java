package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.repositories.ClienteRepository;
import com.joshaby.springboot2backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = repository.findByEmail(email);
        if (cliente == null) {
            throw new UsernameNotFoundException(String.format("Usuário com id %d inválido", cliente.getId()));
        }
        return new UserDetailsImpl(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
