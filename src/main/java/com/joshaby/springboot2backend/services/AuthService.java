package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.repositories.ClienteRepository;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@AllArgsConstructor
public class AuthService {

    private ClienteRepository clienteRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private EmailService emailService;

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado!");
        }
        String newPassword = generateNewPassword();
        cliente.setSenha(passwordEncoder.encode(newPassword));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    private String generateNewPassword() {
        return StringUtils.randomAlphanumeric(8).toLowerCase();
    }
}
